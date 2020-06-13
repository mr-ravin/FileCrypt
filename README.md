# FileCrypt

![FileCrypt Logo](https://github.com/mr-ravin/FileCrypt/blob/master/ic_launcher.png)

FileCrypt is an opensource android application able to perform AES-128 bit encryption on all types of files. When encryption is performed on < filename >, then its encrypted file is created with the name: < filename.filecrypt >. Similarly, when decryption is performed on < filename.filecrypt >, it generates back the original file with the name < fc_filename > . Source code of version 1 is available in "src" directory. It performs encryption /decryption on files without deleting the inputed file (i.e. original file in case of encryption, and encrypted file in case of decryption.) FileCrypt uses AES-128 bit with CBC, it is considered better for providing security for general use cases, please do consult your cybersecurity adviser if looking for a very specific usecase.
 
##### Application Developer: [Ravin Kumar](http://mr-ravin.github.io)

##### Algorithm Detail: AES-128 bit (using AES/CBC/PKCS5Padding and PBKDF2WithHmacSHA1)
 
#### Download FileCrypt from Google PlayStore:
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="30%" height="30%" target="_blank">]()

#### Working Demonstration:


[![Working Demonstration](https://github.com/mr-ravin/FileCrypt/blob/master/FileCrypt_demo.gif)](https://www.youtube.com/watch?v=xY9OeXeQOWI)

![android application view 1](https://github.com/mr-ravin/FileCrypt/blob/master/FileCrypt_main_vfin.png)

![android application view 2](https://github.com/mr-ravin/FileCrypt/blob/master/FileCrypt_details_vfin.png)

### Working Android Application Video
The working demonstration of FileCrypt is available at [Youtube](https://www.youtube.com/watch?v=xY9OeXeQOWI).

##### Note: This android application uses AES-128 bit for encryption which is considered good for general purpose use, please do consult a security expert regarding your required security standards.

#### App parameter related details:
```
Iteration: increasing the value will increase the security, select it based on the computation power of your device.
People often select it around 100,000.
```

##### Note: In case you set your own Iteration value please do remember it, as for decryption this parameter must be same as that of used during encryption.

##### Note: In the source code, the code of RealPathUtil.java is taken from [link](https://gist.github.com/tatocaster/32aad15f6e0c50311626), which was made opensourced by [Merab Tato Kutalia](https://github.com/tatocaster). This file is used to retrieve file path from uri. 

```python
Copyright (c) 2019 Ravin Kumar
Website: https://mr-ravin.github.io

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation 
files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, 
modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the 
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the 
Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
