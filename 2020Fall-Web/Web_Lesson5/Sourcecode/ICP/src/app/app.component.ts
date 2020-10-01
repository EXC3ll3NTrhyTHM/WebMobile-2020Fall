import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // define list of items
  items = [];

  // Write code to push new item
  submitNewItem(item) {
    this.items.push(item);

    const list = document.getElementById('list');
    // Returns final element in array and sets it equal to item
    item = this.items[this.items.length - 1];
    const li = document.createElement('li');

    li.setAttribute('style', '  position: relative;\n' +
      '  height: 35px;\n' +
      '  line-height: 40px;\n' +
      '  list-style: none;\n' +
      '  margin: 0 0 10px;\n' +
      '  padding: 0 20px 0 40px;\n' +
      '  color: #333;\n' +
      '  overflow: hidden;\n' +
      '  background: #f1f1f1;\n' +
      '  -moz-border-radius: 2px;\n' +
      '  -webkit-border-radius: 2px;\n' +
      '  border-radius: 2px;\n' +
      '  -webkit-box-shadow: 0px 2px 2px 0px rgba(204, 204, 204, 0.5);\n' +
      '  -moz-box-shadow: 0px 2px 2px 0px rgba(204, 204, 204, 0.5);\n' +
      '  box-shadow: 0px 2px 2px 0px rgba(204, 204, 204, 0.5);');

    li.innerHTML = item + '<button (click)="deleteItem($event)"></button>';
    list.appendChild(li);
  }

  // Write code to complete item
  completeItem(event) {
    event.target.setAttribute('class', 'completed');
  }

  // Write code to delete item
  deleteItem(event) {
    event.target.parentElement.remove();
  }

  timerDeleteItem(id) {
    document.getElementById(id).parentElement.remove();
  }
}
