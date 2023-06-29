# FileCrypt

![FileCrypt Logo](https://github.com/mr-ravin/FileCrypt/blob/master/ic_launcher.png)

FileCrypt is an opensource android application able to perform AES-128 bit encryption on all types of files. 

Steps to follow, for android target sdk 33-
```
1. After Installation, provide the File and Media permission, otherwise app will crash at startup.
2. Encrypted file will be stored inside Documents folder with name FileCrypt_filename.
3. Decrypted file will be stored inside Documents folder with original filename.
```

Steps to follow, for android target sdk 29-
```
1. After Installation, provide the File and Media permission, otherwise app will crash at startup.
2. Encrypted file will be stored extension of ".filecrypt".
3. Decrypted file will be stored inside Documents folder with original filename.
```

Note- This app does not delete or remove the input file used for encryption or decryption; Instead, this app writes the file generated after encryption/decryption operation.

##### Application Developer: [Ravin Kumar](http://mr-ravin.github.io)

##### Algorithm Detail: AES-128 bit (using AES/CBC/PKCS5Padding and PBKDF2WithHmacSHA1)

1. ##### Download FileCrypt from Google PlayStore, Target SDK=33:
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="30%" height="30%" target="_blank">](https://play.google.com/store/apps/details?id=ravin.dev.filecrypt)

2. ##### Download FileCrypt from Google PlayStore, Target SDK=29:
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="30%" height="30%" target="_blank">](https://play.google.com/store/apps/details?id=ravin.developer.filecrypt)

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
Copyright (c) 2019-2023 Ravin Kumar
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
