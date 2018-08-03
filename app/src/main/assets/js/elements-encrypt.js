var element = {
 "SchoolCode":’XYZ’,
 "Grade": ‘A’
 };

function encryptElementsData(SchoolCode, Grade) {
          this.element.SchoolCode = SchoolCode;
          this.element.Grade = Grade;
          console.log(JSON.stringify(this.element));
          const key = keyGenerator();
          const cryptData = CryptoJS.AES.encrypt(JSON.stringify(this.childDetails), key).toString();
          const wordArray = CryptoJS.enc.Utf8.parse(cryptData.slice(0, 10) + key + cryptData.slice(10));
          const base64 = CryptoJS.enc.Base64.stringify(wordArray);
          return encodeURIComponent(base64);
}

function keyGenerator() {
          let randomKey = '';
          const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+=?.,<>|\{}[]';
          for (let i = 0; i < 17; i++) {
              randomKey += possible.charAt(Math.floor(Math.random() * possible.length));
          }
          return randomKey;
}