function decryptData(data) {
          const parsedWordArray = CryptoJS.enc.Base64.parse(decodeURIComponent(data));
          const parsedStr = parsedWordArray.toString(CryptoJS.enc.Utf8);
          const cryptCode = parsedStr.slice(0, 10) + parsedStr.slice(27);
          const key = parsedStr.slice(10, 27);
          const bytes = CryptoJS.AES.decrypt(cryptCode, key);
          //bytes.toString(CryptoJS.enc.Utf8)
          return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
}