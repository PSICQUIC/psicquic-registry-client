from urllib.request import urlopen

urlStr = 'http://www.ebi.ac.uk/Tools/webservices/psicquic/registry/registry?action=ACTIVE&format=txt'
try:
    fileHandle = urlopen(urlStr)
    content = fileHandle.read()
    fileHandle.close()
except IOError:
    print('Cannot open URL ' + urlStr)
    content = ''

lines = content.splitlines()

for line in lines:
    line = str( line, encoding='utf8' )
    fields = line.split('=')
    print(fields[0] + ' ---> ' + fields[1])
