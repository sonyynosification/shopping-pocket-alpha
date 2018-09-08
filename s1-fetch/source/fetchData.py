import urllib.request
import json
from fetchingmodule import bitish,gucci,fptshop,converse,nemfashion
from bs4 import  BeautifulSoup
from unidecode import unidecode
import string

with open("json/bitish.json","w") as f:
    json.dump(bitish("https://theme.hstatic.net/1000230642/1000378739/14/storesjs.json?v=44"),f)
with open("json/gucci.json","w") as f:
    json.dump(gucci("https://www.gucci.com/us/en/store/all?south=9.230547733950115&west=103.44103055390622&north=11.402959478719966&east=108.59911160859372"),f)
with open("json/fpt.json","w") as f:
    json.dump(fptshop(),f)
with open("json/converse.json","w") as f:
    json.dump(converse("https://www.converse.com.vn/stores.html"),f)
with open("json/nem.json","w") as f:
    json.dump(nemfashion("https://nemshop.vn/thong-tin/he-thong-showroom"),f)

print("/n/nFinished dumping!")