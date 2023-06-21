import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { tap } from 'rxjs';
import { MatPaginator } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/interfaces/models/userResponse.interface';
import { UserConfirmDialogComponent } from '../user-confirm-dialog/user-confirm-dialog.component';
import { EditUserFromAdminDialogComponent } from '../edit-user-from-admin-dialog/edit-user-from-admin-dialog.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { UserService } from 'src/app/shared/services/user.service';


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

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap((event) => this.loadData(event.pageIndex, event.pageSize))
      )
      .subscribe();
  }


  loadData(page: number, pageSize: number){
    this.userService.getAllUsers(page,pageSize).subscribe(resp=>{
      this.userList = resp.content;
      this.totalElements = resp.totalElements
    })
  }
  disableUser(user: User){
    this.userService.changeUserStatus(user.id).subscribe(resp =>{
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

  createUser() {
    const dialogRef = this.dialog.open(CreateUserComponent, {});
    dialogRef.afterClosed().subscribe(() => {
      this.paginator.pageIndex = 0;
      this.loadData(this.paginator.pageIndex, this.paginator.pageSize);
    });
  }


}
