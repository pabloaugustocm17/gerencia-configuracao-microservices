import {Component} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { BookService } from 'src/app/shared/services/book.service';
import { BookPayload } from '../../shared/interfaces/payload-book.interface';
import { Book } from 'src/app/shared/interfaces/book.interface';
import { map } from 'rxjs';

/**
 * @title Basic use of `<table mat-table>`
 */
@Component({
  selector: 'app-book-view',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './book-view.component.html',
  styleUrl: './book-view.component.css'
})
export class BookViewComponent {
  displayedColumns: string[] = [ 'name', 'author', 'launch_date','description','price'];
  glb = new BookService
  dataSource: BookPayload[] = [];

   formatDate(date : string){
    const dateAux = new Date(date);
    let dia:string
    let mes:string

    if(dateAux.getDate() < 10){
      dia = `0${dateAux.getDate()}`
    }else{
      dia = `${dateAux.getDate()}`
    }

    if(dateAux.getMonth()+1 < 10){
      mes = `0${dateAux.getMonth()+1}`
    }else{
      mes = `${dateAux.getMonth()+1}`
    }
    
    return dia + '/' + mes + '/' + dateAux.getFullYear();
  }

  ngOnInit(){
    this.glb.getAllBook().pipe(map((books)=>{
      return books.map((book)=>{
      let newBook: Book = {
        id: book.id,
        name: book.name,
        author: book.author,
        launch_date: new Date(book.launch_date),
        description: book.description,
        price: book.price
      };
      return newBook;
      })
    })).subscribe((data : Book[]) => {
      this.dataSource = data
      console.log(this.dataSource)
    },);


  }
  

}


