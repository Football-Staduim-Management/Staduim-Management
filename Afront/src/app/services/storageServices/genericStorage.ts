import { Subject, Observable } from 'rxjs';

export class GenericStorage {

    protected storageSub = new Subject<String>();


    watchStorage(): Observable<any> {
        return this.storageSub.asObservable();
    }

    protected setState(key: string, data: any) {
        localStorage.setItem(key, data);
        this.storageSub.next('SET');
    }

    protected getState(key: string): any {
        let obj = localStorage.getItem(key)
        return obj;
    }

    protected deleteState(key) {
        localStorage.removeItem(key);
        this.storageSub.next('DELETE');
    }

    protected updateState(key:string,data : any){
        localStorage.setItem(key, data);
        this.storageSub.next('UPDATE');
    }
}
