from mininet.topo import Topo
from mininet.link import Link

class MyTopo( Topo ):
	"Simple Topology example."

	def __init__( self ):
		"Create custom topo."

		#Initialize topology
		Topo.__init__( self )

		#Add hosts
		h1 = self.addHost('h1',mac='00:00:00:00:00:03')
		h2 = self.addHost('h2',mac='00:00:00:00:00:04')
		h3 = self.addHost('h3',mac='00:00:00:00:00:05')

		#Add switches
		s1 = self.addSwitch('s1',mac='00:00:00:00:00:01')
		s2 = self.addSwitch('s2',mac='00:00:00:00:00:02')

		#Host to switch links
		self.addLink(s1,h1)
		self.addLink(s2,h2)
		self.addLink(s2,h3)

		#Switch to Switch link
		self.addLink(s1,s2)


topos = { 'mytopo' : (lambda : MyTopo() ) }
