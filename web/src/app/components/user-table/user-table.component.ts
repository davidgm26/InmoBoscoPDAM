import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/interfaces/models/userResponse.interface';
import { UserService } from 'src/app/services/user.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { EditUserFromAdminDialogComponent } from 'src/app/shared/components/edit-user-from-admin-dialog/edit-user-from-admin-dialog.component';
import { UserConfirmDialogComponent } from 'src/app/shared/components/user-confirm-dialog/user-confirm-dialog.component';
import { UserTypeSelectionDialogComponent } from 'src/app/shared/components/user-type-selection-dialog/user-type-selection-dialog.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  page = 0;
  pageSize = 5;
  totalElements = 0;
  userList: User[] = [];

  constructor(
    private userService: UserService,
    private dialog: MatDialog,
    private ngxtoast: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadData(this.page, this.pageSize)
  }

  loadData(page: number, pageSize: number){
    this.userService.getAllUsers(page,pageSize).subscribe(resp=>{
      this.userList = resp.content;
    })
  }
  disableUser(user: User){
    this.userService.changeUserStatus(user.id).subscribe(resp =>{
    debugger
    user.enabled = resp.enabled;
    },(error: any) =>{
      this.ngxtoast.error('No se ha podido cambiar el estado de esta cuenta','Error')
    }
    )
  }

  deleteUser(user: User){
    const dialogRef = this.dialog.open(UserConfirmDialogComponent, {
      data: user
    });

    dialogRef.componentInstance.confirmed.subscribe(() => {
      this.loadData(this.page, this.pageSize);
    });
  }

  editUser(user: User){
      this.dialog.open(EditUserFromAdminDialogComponent,{
      data: user
    });
  }

  createUser(){
    this.dialog.open(UserTypeSelectionDialogComponent,{});
  }


}
