import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../../services/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dialoug-upload-image',
  templateUrl: './dialoug-upload-image.component.html',
  styleUrls: ['./dialoug-upload-image.component.css'],
})
export class DialougUploadImageComponent implements OnInit {
  avatar!: File;

  constructor(
    private dialogRef: MatDialogRef<DialougUploadImageComponent>,
    private userService: UserService,
    private ngxToast: ToastrService
  ) {}
  ngOnInit(): void {}

  submit() {
    this.userService.changeProfileImg(this.avatar).subscribe(
      (resp) => {},
      (error: any) => {
        this.ngxToast.error(error.error.message);
      }
    );
    this.close();
  }
  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      this.avatar = event.target.files[0];
    }
  }
  close() {
    this.dialogRef.close();
  }
}
