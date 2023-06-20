import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imgProfile'
})
export class ImgProfilePipe implements PipeTransform {

  transform(imagen: string): unknown {
    const urlBase = 'http://localhost:8080'
    if(!imagen) {
      return '../../../../assets/default.jpg';
    } else if (imagen) {
      return `${urlBase}/download/${imagen}`;
    } else {
      return '../../../assets/default.jpg';
    }
  }

}
