import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateUserResponse } from 'src/app/interfaces/dtos/createUserResponse';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { DialougUploadImageComponent } from 'src/app/shared/components/dialoug-upload-image/dialoug-upload-image.component';
import { UserService } from 'src/app/shared/services/user.service';
import { EditUserInfoDialogComponent } from '../edit-user-info-dialog/edit-user-info-dialog.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user!:CreateUserResponse;
  favouriteList: Property [] = [];
  constructor(
    private userService: UserService,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.loadData();
  }
  changeImg(){
    const dialogRef = this.dialog.open(DialougUploadImageComponent, {
      width: '550px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El diálogo fue cerrado');
      this.loadData();
    });

  }
  loadData(){
    this.userService.getCurrentUser().subscribe(
      resp => {
        this.user = resp;
      }
      ),(error:any) => {
        console.log(error.error.message)
      }
      this.userService.getFavouritesList().subscribe(
        resp => this.favouriteList = resp.content
      )
  }

  deleteFromFavourites(propertyId: number) {
    const index = this.favouriteList.findIndex(prop => prop.id === propertyId);
    if (index !== -1) {
      this.favouriteList.splice(index, 1);
    }
    this.loadData()
  }

    currentScroll = 0;

  scrollLeft() {
    this.currentScroll += 200;
  }

  scrollRight() {
    this.currentScroll -= 200;
   }

   editUserInfo(){
    const dialogRef = this.dialog.open(EditUserInfoDialogComponent, {
      width: '500px',
      data: this.user
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
