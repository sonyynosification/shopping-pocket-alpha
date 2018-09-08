import urllib.request
from urllib.request import Request, urlopen
import requests
import json
from bs4 import  BeautifulSoup
from unidecode import unidecode
import string

def bitish(urlapi):
    with urllib.request.urlopen(urlapi) as url:
        data = json.loads(url.read().decode("utf8"))
    return data

def gucci(urlapi):
        reqs = Request(urlapi,headers={"User-Agent":"Mozilla/5.0"})
        content = urlopen(reqs)
        data = json.loads(content.read())
        return data

def fptshop():
    with open("resources/fptshops.htm","r",encoding="utf-8",newline='') as fp:
        soup = BeautifulSoup(fp,"html.parser")
    tim = soup.find("div",class_="shopv2-list-shop").find("ul")
    tim2 = tim.find_all("a")
    res =[]
    for s in tim2:
        res.append(str(s))
    return res

def converse(urlapi):
    content = requests.get(urlapi)
    data = content.text
    soup = BeautifulSoup(data,"html.parser")
    tim = soup.find("div",class_="provincial-col").find("ul")
    tim2 = tim.find_all("a")
    res = []
    for s in tim2:
        res.append(str(s))
    return res

def nemfashion(urlapi):
    content = requests.get(urlapi)
    data = content.text
    soup = BeautifulSoup(data,"html.parser")
    tim = soup.find("div",class_="row")
    tim2 = tim.find_all("p")
    res = []
    for s in tim2:
        res.append(str(s))
    res = unidecode(u"res")
    return res
    