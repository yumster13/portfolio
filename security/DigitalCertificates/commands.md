# Exo 1
## Actor: BE Education Authority
Generating private, public keys and a self-signed certificate
```shell
# cwd: /be_education_authority/private

openssl ecparam -name prime256v1 -genkey -noout -out be_ea_key.pem


#cwd : /be_education_authority/public
openssl req -new -x509 -key ../private/be_ea_key.pem -out be_ea_cert.pem -days 3650

> BE
> Hainaut
> Charleroi
> Belgian Education Authority
> 
> Belgian Education Authority DSC01
>

openssl ec -in ../private/be_ea_key.pem -pubout -out public_key.pem
```

# Exo 2
## Actor : BE Education Authority 
Sending the certificate to the central authority
```shell
# cwd: /
cp be_education_authority/public/be_ea_cert.pem central_education_authority/tmp/
```

## Actor : Central Education Authority:
Accepting the DSC certificate
```shell
# cwd: /central_education authority
cp tmp/be_ea_cert.pem pubrepo
# cwd: /central_education authority/pubrepo
mv be_ea_cert.pem $(openssl x509 -in be_ea_cert.pem -noout -fingerprint -sha1 | cut -d= -f2 | tr -d ':').pem
```


# Exo 3
## Actor: HE2B School
Generating unsigned DPSE
```shell
# cwd: /he2b_school/tmp
echo "Taha" >> 58744.txt
echo "Ahmed" >> 58744.txt
echo "58744" >> 58744.txt
echo "BE" >> 58744.txt
echo "Haute Ecole Bruxelles-Brabant" >> 58744.txt
echo $(date +%Y-%m-%d) >> 58744.txt
echo $(date -d "+10 years" +%Y-%m-%d) >> 58744.txt
```

# Exo 4
## Actor: HE2B School
setup:
```shell
# cwd: /
cp he2b_school/tmp/58744.txt be_education_authority/tmp/
```

## Actor: Be Education Authority
Computing the signature and storing it
```shell
# cwd: /be_education_authority/tmp

openssl dgst -sign ../private/be_ea_key.pem -out 58744.signature.bin 58744.txt

openssl base64 -in 58744.signature.bin -out 58744.signature.txt

openssl x509 -in ../public/be_ea_cert.pem -noout -fingerprint -sha1 | cut -d= -f2 | tr -d ':' > 58744.dscfingerprint.txt

```


# Exo 5
## Actor: Be Education Authority
setup:
```shell
# cwd: /
cp be_education_authority/tmp/58744.{signature,dscfingerprint}.txt he2b_school/tmp/
```

## Actor: He2b School
Assembling the complete DPSE
```shell
#cwd: he2b_school/
cp tmp/58744.txt dpse/58744.dpse.txt
cat tmp/58744.signature.txt >> dpse/58744.dpse.txt
cat tmp/58744.dscfingerprint.txt >> dpse/58744.dpse.txt
cat dpse/58744.dpse.txt | qrencode -o qr_dpse/58744.png
```

## Actor:


# Exo 6
## Actor: HE2B_School
Signature Verification
```shell
# cwd: /he2b_school
openssl base64 -d -in tmp/58744.signature.txt -out tmp/58744.signature.decoded.txt

openssl dgst -verify ../be_education_authority/public/public_key.pem -signature tmp/58744.signature.decoded.txt tmp/58744.txt
 
```

# Exo 7

332454 Valid

807125 Invalid, the signature doesn't match, the DPSE expired

848738 Invalid, the DPSE expired

867160 Valid

910089 Invalid, the signature doesn't match, The DPSE expired
