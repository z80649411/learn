import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(list: any[], filterFieid: string, keyword: string): any {
    if (!filterFieid || !keyword) {
      return list;
    }
    return list.filter(item => {
      let fiedlValue = item[filterFieid];
      return fiedlValue.indexOf(keyword) >= 0;
    });
  }

}
