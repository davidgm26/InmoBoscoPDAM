import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'img'
})
export class ImgPipe implements PipeTransform {

  transform(imagen: string): unknown {
    const urlBase = 'http://localhost:8080'
    if(!imagen) {
      return '../../../../assets/ErrorImageProperty.jpg';
    } else if (imagen) {
      return `${urlBase}/download/${imagen}`;
    } else {
      return '../../../assets/ErrorImageProperty.jpg';
    }
  }

}
