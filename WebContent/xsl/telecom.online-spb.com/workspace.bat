md xsl 
cd xsl
md shops.online-spb.com
cd shops.online-spb.com
md images
md mail
md ru
md en
cd ../
cd ../

rem xcopy . ss

xcopy calendar.css xsl\shops.online-spb.com
xcopy caption.js xsl\shops.online-spb.com
xcopy constant.css xsl\shops.online-spb.com
xcopy datepicker.js xsl\shops.online-spb.com
xcopy jquery.js  xsl\shops.online-spb.com
xcopy mootools.js  xsl\shops.online-spb.com
xcopy template.css  xsl\shops.online-spb.com
copy /z  images  xsl\shops.online-spb.com\images
copy /z  mail  xsl\shops.online-spb.com\mail
copy /z  ru  xsl\shops.online-spb.com\ru
copy /z  en  xsl\shops.online-spb.com\en



del calendar.css
del caption.js 
del constant.css 
del datepicker.js 
del jquery.js  
del mootools.js  
del template.css  
rd /s /q images
rd /s /q mail 
rd /s /q ru 
rd /s /q en