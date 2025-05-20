import { Injectable } from '@angular/core';
import firebase from 'firebase/compat/app';
import 'firebase/compat/storage';

import { environment } from '../../environments/environment';
import { Observable, from, throwError } from 'rxjs';

firebase.initializeApp(environment.firebaseConfig);

@Injectable({
  providedIn: 'root',
})
export class FirebaseStorageService {
  public storageRef = firebase.app().storage().ref();

  constructor() {}

  uploadFile$(file: File): Observable<string> {
    return new Observable<string>(observer => {
      const name = file.name;
      const reader = new FileReader();

      reader.readAsDataURL(file);

      reader.onloadend = () => {
        const imageBase64 = reader.result as string;

        from(
          this.storageRef
            .child(name)
            .putString(imageBase64, 'data_url')
            .then(response => response.ref.getDownloadURL())
        ).subscribe({
          next: url => {
            observer.next(url);
            observer.complete();
          },
          error: err => observer.error(err),
        });
      };

      reader.onerror = err => observer.error(err);
    });
  }
}
