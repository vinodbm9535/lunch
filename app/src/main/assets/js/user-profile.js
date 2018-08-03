var userProfile = {
};

function encryptData() {
          console.log(JSON.stringify(this.childDetails));
          const key = keyGenerator();
          const cryptData = CryptoJS.AES.encrypt(JSON.stringify(this.childDetails), key).toString();
          const wordArray = CryptoJS.enc.Utf8.parse(cryptData.slice(0, 10) + key + cryptData.slice(10));
          const base64 = CryptoJS.enc.Base64.stringify(wordArray);
          return encodeURIComponent(base64);
}

function decryptData(data) {
          const parsedWordArray = CryptoJS.enc.Base64.parse(decodeURIComponent(data));
          const parsedStr = parsedWordArray.toString(CryptoJS.enc.Utf8);
          const cryptCode = parsedStr.slice(0, 10) + parsedStr.slice(27);
          const key = parsedStr.slice(10, 27);
          const bytes = CryptoJS.AES.decrypt(cryptCode, key);
          //bytes.toString(CryptoJS.enc.Utf8)
          return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
        }