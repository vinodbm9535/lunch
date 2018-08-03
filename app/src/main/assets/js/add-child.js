var childDetails = {
"ChildId": "",
"ChildType": 1,
"ChildFirstName":"Keshava",
"ChildLastName":"Valluri",
"SchoolCode":"XYZ",
"Allergies":"1,2,3,4",
"Favorites":"Some Fav",
"SpecialInstructions":"Some Text",
"Grade":"1",
"Element":"El1",
"Type": 1
 };

function encryptChildData(ChildId, ChildType, ChildFirstName, ChildLastName, SchoolCode, Favorites, SpecialInstructions, Grade,Element,Type ) {
          this.childDetails.ChildId = ChildId;
          this.childDetails.ChildType = ChildType;
          this.childDetails.ChildFirstName = ChildFirstName;
          this.childDetails.ChildLastName = ChildLastName;
          this.childDetails.SchoolCode = SchoolCode;
          this.childDetails.Favorites = Favorites;
          this.childDetails.SpecialInstructions = SpecialInstructions;
          this.childDetails.Grade = Grade;
          this.childDetails.Element = Element;
          this.childDetails.Type = Type;
          console.log(JSON.stringify(this.childDetails));
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