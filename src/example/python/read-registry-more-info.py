import urllib2
import xml.etree.ElementTree as ET

urlStr = 'http://www.ebi.ac.uk/Tools/webservices/psicquic/registry/registry?action=STATUS&format=xml'

# read the file
try:
    fileHandle = urllib2.urlopen(urlStr)
    content = fileHandle.read()
    fileHandle.close()
except IOError:
    print 'Cannot open URL' % urlStr
    content = ''

# Create the XML reader
root = ET.fromstring(content)
xmlns = '{http://hupo.psi.org/psicquic/registry}'

totalCount = 0
serviceCount = 0
activeCount = 0

for service in root.findall(xmlns + 'service'):
    # Getting some of the elements for each node
    name = service.find(xmlns + 'name').text
    active = service.find(xmlns + 'active').text
    interactionCount = service.find(xmlns + 'count').text
    restUrl = service.find(xmlns + 'restUrl').text
    restExample = service.find(xmlns + 'restExample').text

    print 'Service: ' + name + ' =========================================================================='
    print '\tActive: ' + active
    print '\tEvidences: ' + interactionCount
    print '\tREST URL: ' + restUrl
    print '\tREST Example: ' + restExample

    totalCount = totalCount + int(interactionCount)
    serviceCount = serviceCount + 1

    if active.lower() == 'true':
       activeCount = activeCount + 1

# Print totals
print '\nTotal evidences: ' + str(totalCount)
print 'Total services: ' + str(serviceCount)
print '\tActive: ' + str(activeCount)
