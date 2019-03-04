#!/usr/bin/python/

from mininet.net import Mininet
from mininet.node import Controller, RemoteController, UserSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel
from mininet.link import Link, TCLink

def topology():
	"Simple Topology example."
	net = Mininet(topo=None,
		      build=False)
	
	c0 = net.addController('c0',controller=RemoteController,ip='127.0.0.1',port=6653)
	
	h1 = net.addHost('h1',mac='00:00:00:00:00:03')
	h2 = net.addHost('h2',mac='00:00:00:00:00:04')
	h3 = net.addHost('h3',mac='00:00:00:00:00:05')
	
	s1 = net.addSwitch('s1',mac='00:00:00:00:00:01')
	s2 = net.addSwitch('s2',mac='00:00:00:00:00:02')

	net.addLink(s1,s2)	
	net.addLink(s1,h1)
	#Link(h1,s1,intfName1='h1-eth0',intfName2='s1-eth0')	
	net.addLink(s2,h2)
	#Link(h2,s2,intfName1='h2-eth0')
	#Link(h2,s2,intfName1='h2-eth1')	
	#h2.cmd('ifconfig h2-eth0:0 10.0.0.250 up')
	
	net.addLink(s2,h3)
	#Link(h3,s2,intfName1='h3-eth0')
	#Link(h3,s2,intfName1='h3-eth1')
	#h3.cmd('ifconfig h3-eth0:0 10.0.0.250 up')

	c0.start()
	s1.start([c0])
	s2.start([c0])
	net.build()
	CLI(net)
	net.stop()

if __name__ =='__main__':
    setLogLevel('info')
    topology()
