import fileinput
import re
import os
from pprint import pprint
import requests

'''
url = 'https://www.codigo-postal.pt/braga/'
names = ['se','espinho','padim-da-graça','adaufe','pousada','santa-lucrecia-de-algeriz','arcos','arentim','real','aveleda',
'cabreiros','celeiros','cividade','crespos','cunha','sao-joao-do-souto','dume','escudeiros','sao-jose-de-sao-lazaro',
'esporoes','sao-pedro-este','penso-santo-estevao','ferreiros','figueiredo','sao-mamede-este','fradelos','fraiao','frossos',
'gondizalves','gualtar','guisande','passos-sao-juliao','lamas','lamacaes','lomar','maximinos','sao-paio-merelim','sao-pedro-merelim',
'mire-de-tibaes','morreira','navarra','nogueira','nogueiro','sao-pedro-oliveira','palmeira','panoias','parada-de-tibaes','pedralva',
'sao-vicente-penso','priscos','ruilhe','sao-vicente','sao-victor','sequeira','semelhe','sobreposta','tadim','tebosa','tenoes',
'trandeiras','vilaca','vimieiro']

for name in names:
	aux = url + name + '/'
	print(aux)
	r = requests.get(aux, allow_redirects=True)
	open(name+'.txt', 'wb').write(r.content)
	exp1 = re.compile("(1 de )(\d+)")
	text = "".join(fileinput.input(name+'.txt'))
	matches = re.findall(exp1, text)
	try:
		x,y = matches[0]
	except:
		y = 1
	limit = int(y)
	print(limit)
	i = 2
	while (i<=limit):
		try:
			aux = url + name + "/" + str(i) + ".html"
			r = requests.get(aux, allow_redirects=True)
			open(name+str(i)+'.txt','wb').write(r.content)
			i += 1
		except:
			continue
			'''


exp = re.compile("(cp\">)(\d{4}-\d{3})")

path = r'/home/bruno/Desktop/html'
dirs = os.listdir(path)
matches = []

for file in dirs:
	file = file.replace("\'","")
	text = "".join(fileinput.input(file))
	matches += re.findall(exp, text)

for x,y in matches:
	pprint(y)