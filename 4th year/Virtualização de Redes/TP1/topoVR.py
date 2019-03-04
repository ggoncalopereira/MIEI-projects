from mininet.topo import Topo

class MyTopo( Topo ):
	"Simple Topology example."

	def __init__( self ):
		"Create custom topo."

		#Initialize topology
		Topo.__init__( self )

		#Add hosts
		h1 = self.addHost('h1')
		h2 = self.addHost('h2')
		h3 = self.addHost('h3')

		#Add switches
		s1 = self.addSwitch('s1')
		s2 = self.addSwitch('s2')

		#Host to switch links
		self.addLink(s1,h1)
		self.addLink(s2,h2)
		self.addLink(s2,h3)

		#Switch to Switch link
		self.addLink(s1,s2)


topos = { 'mytopo' : (lambda : MyTopo() ) } 
