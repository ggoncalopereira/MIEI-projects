from mininet.topo import Topo
from mininet.link import Link

class MyTopo( Topo ):
	"Simple Topology example."

	def __init__( self ):
		"Create custom topo."

		#Initialize topology
		Topo.__init__( self )

		#Add Clients
		cl1 = self.addHost('cl1',ip='10.0.0.1',mac='00:00:00:00:00:10')
		cl2 = self.addHost('cl2',ip='10.0.0.2',mac='00:00:00:00:00:11')

		#Add File Servers
		fs1 = self.addHost('fs1',ip='10.0.0.3',mac='00:00:00:00:00:12')
		fs2 = self.addHost('fs2',ip='10.0.0.4',mac='00:00:00:00:00:13')
		
		#Add Dns		
		dns1 = self.addHost('dns1',ip='10.0.0.5',mac='00:00:00:00:00:14')
		dns2 = self.addHost('dns2',ip='10.0.0.6',mac='00:00:00:00:00:15')

		#Add switches
		s1 = self.addSwitch('s1',mac='00:00:00:00:00:01')
		s2 = self.addSwitch('s2',mac='00:00:00:00:00:02')
		s3 = self.addSwitch('s3',mac='00:00:00:00:00:03')
		s4 = self.addSwitch('s4',mac='00:00:00:00:00:04')
		s5 = self.addSwitch('s5',mac='00:00:00:00:00:05')
		s6 = self.addSwitch('s6',mac='00:00:00:00:00:06')
		s7 = self.addSwitch('s7',mac='00:00:00:00:00:07')
		s8 = self.addSwitch('s8',mac='00:00:00:00:00:08')
		s9 = self.addSwitch('s9',mac='00:00:00:00:00:09')

		#Switch 1 links to file server 1 and 2
		self.addLink(s1,fs1)
		self.addLink(s1,fs2)
		
		#Switch 2 links to client 1
		self.addLink(s2,cl1)

		#Switch 3 links to dns1
		self.addLink(s3,dns1)

		#Switch 7 links to dns2
		self.addLink(s7,dns2)

		#Switch 9 links to client 2		
		self.addLink(s9,cl2)

		#Switch 1 to other Switchs links
		self.addLink(s1,s2)
		self.addLink(s1,s4)
		
		#Switch 2 to other Switchs links
		self.addLink(s2,s3)
		self.addLink(s2,s5)

		#Switch 3 to other Switch link
		self.addLink(s3,s6)

		#Switch 4 to other Switchs links
		self.addLink(s4,s5)
		self.addLink(s4,s7)

		#Switch 5 to other Switchs link
		self.addLink(s5,s6)
		self.addLink(s5,s8)

		#Switch 6 to other Switchs link
		self.addLink(s6,s9)
		
		#Switch 7 to other Switchs link
		self.addLink(s7,s8)

		#Switch 8 to other Switchs link
		self.addLink(s8,s9)

topos = { 'mytopo' : (lambda : MyTopo() ) }
