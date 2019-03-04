package net.floodlightcontroller.mactracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketOut;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.types.ArpOpcode;
import org.projectfloodlight.openflow.types.EthType;
import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.IpProtocol;
import org.projectfloodlight.openflow.types.MacAddress;
import org.projectfloodlight.openflow.types.OFPort;
import org.projectfloodlight.openflow.types.TransportPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;

import net.floodlightcontroller.packet.ARP;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.packet.IPv4;
import net.floodlightcontroller.packet.TCP;
import net.floodlightcontroller.packet.UDP;

public class MACTracker implements IFloodlightModule, IOFMessageListener {

	protected IFloodlightProviderService floodlightProvider;
	protected Set<Long> macAddresses;
	protected static Logger logger;
	private boolean flag;
	
	@Override
	public String getName() {
		return MACTracker.class.getSimpleName();
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		if(type.equals(OFType.PACKET_IN) && name.equals("forwarding")) {
			return true;
		}
			
		return false;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> l =
				new ArrayList<Class<? extends IFloodlightService>>();
		l.add(IFloodlightProviderService.class);
		return l;
	}

	@Override
	public void init(FloodlightModuleContext context)
			throws FloodlightModuleException {
		floodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);
		macAddresses = new ConcurrentSkipListSet<Long>();
		logger = LoggerFactory.getLogger(MACTracker.class);
		flag = false;
	}

	@Override
	public void startUp(FloodlightModuleContext context)
			throws FloodlightModuleException {
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
	}

	/*
	 * Overridden IOFMessageListener's receive() function.
	 */
	@Override
	public Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx) {
	    switch (msg.getType()) {
	    case PACKET_IN:
	        Ethernet eth = IFloodlightProviderService.bcStore.get(cntx, IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
	 
	        if (eth.getEtherType() == EthType.IPv4) {
	            IPv4 ipv4 = (IPv4) eth.getPayload();
	            
	            if (ipv4.getProtocol() == IpProtocol.TCP) {
	            	if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:05") &&
			        		ipv4.getDestinationAddress().toString().equals("10.0.0.251") &&
			        		eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff") ){ //cliente 1 para ftp

		                Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
			        	
	                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:03")); //ftp1 only
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.251"));
	                	
		                l2.setPayload(l3);
		            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }
	                else if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:06") &&
			        		ipv4.getDestinationAddress().toString().equals("10.0.0.251") &&
			        		eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff")){ //cliente 2 para ftp

		                Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
			        	
	                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:04")); //ftp2 only
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.251"));
	                	
		                l2.setPayload(l3);
		            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }
	            }
	            
	            if (ipv4.getProtocol() == IpProtocol.UDP) {
			        if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:05") &&
			           eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff") &&
			           ipv4.getDestinationAddress().toString().equals("10.0.0.250")){ //cliente 1 para dns
		                System.out.println(eth.toString());
			        	Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
	                	flag = !flag;
	                	
	                	if(flag){
		                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:01")); //dns1 
		                }
		                else{
		                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:02")); //dns2
		                }
	                	
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.250"));

		                l2.setPayload(l3);
		                	            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }

	                else if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:06") &&
			        		eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff") &&
			        		ipv4.getDestinationAddress().toString().equals("10.0.0.250")){ //cliente 2 para dns

		                Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
			        	
	                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:01"));
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.250"));
	                	
		                l2.setPayload(l3);
		            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }
	                else if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:05") &&
			        		ipv4.getDestinationAddress().toString().equals("10.0.0.251") &&
			        		eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff") ){ //cliente 1 para ftp

		                Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
			        	
	                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:03"));
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.251"));
	                	
		                l2.setPayload(l3);
		            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }
	                else if(eth.getSourceMACAddress().toString().equals("00:00:00:00:00:06") &&
			        		ipv4.getDestinationAddress().toString().equals("10.0.0.251") &&
			        		eth.getDestinationMACAddress().toString().equals("ff:ff:ff:ff:ff:ff")){ //cliente 2 para ftp

		                Ethernet l2 = (Ethernet) eth.clone();
			        	IPv4 l3 = (IPv4) ipv4.clone();
			        	
	                	l2.setDestinationMACAddress(MacAddress.of("00:00:00:00:00:03"));
	                	l3.setDestinationAddress(IPv4Address.of("10.0.0.251"));
	                	
		                l2.setPayload(l3);
		            	
		            	byte[] serializedData = l2.serialize();
		            	
		            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
		            			.setData(serializedData)
		            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
		            			.setInPort(OFPort.CONTROLLER)
		            			.build();
		            	
		            	sw.write(po);
		            	return Command.STOP;
	                }
	    		}
	 
	        } else if (eth.getEtherType() == EthType.ARP) {
	            ARP arp = (ARP) eth.getPayload();
	            
	           	if(arp.getTargetProtocolAddress().toString().equals("10.0.0.250")
                		&& arp.getOpCode().toString().equals("1")){
                	Ethernet l2 = (Ethernet) eth.clone();
		        	ARP l3 = new ARP();

		            l2.setSourceMACAddress(MacAddress.of("ff:ff:ff:ff:ff:ff"));
		            l2.setDestinationMACAddress(arp.getSenderHardwareAddress());
		            l2.setEtherType(EthType.ARP);
		            
		            l3.setProtocolType(ARP.PROTO_TYPE_IP);
		            l3.setHardwareType(ARP.HW_TYPE_ETHERNET);
		            l3.setSenderProtocolAddress(IPv4Address.of("10.0.0.250"));
		            l3.setSenderHardwareAddress(MacAddress.of("ff:ff:ff:ff:ff:ff"));
		            l3.setTargetProtocolAddress(arp.getSenderProtocolAddress());
		            l3.setTargetHardwareAddress(arp.getSenderHardwareAddress());
		            l3.setOpCode(ArpOpcode.REPLY);
		            l3.setHardwareAddressLength((byte) 0x06);
		            l3.setProtocolAddressLength((byte) 0x04);
	            	

	            	l2.setPayload(l3);
	            	
	            	byte[] serializedData = l2.serialize();
	            	
	            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
	            			.setData(serializedData)
	            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
	            			.setInPort(OFPort.CONTROLLER)
	            			.build();
	            	
	            	sw.write(po);
	            	return Command.STOP;
                }
	           	else if(arp.getTargetProtocolAddress().toString().equals("10.0.0.251")
                		&& arp.getOpCode().toString().equals("1")){
                	Ethernet l2 = (Ethernet) eth.clone();
		        	ARP l3 = new ARP();

		            l2.setSourceMACAddress(MacAddress.of("ff:ff:ff:ff:ff:ff"));
		            l2.setDestinationMACAddress(arp.getSenderHardwareAddress());
		            l2.setEtherType(EthType.ARP);
		            
		            l3.setProtocolType(ARP.PROTO_TYPE_IP);
		            l3.setHardwareType(ARP.HW_TYPE_ETHERNET);
		            l3.setSenderProtocolAddress(IPv4Address.of("10.0.0.251"));
		            l3.setSenderHardwareAddress(MacAddress.of("ff:ff:ff:ff:ff:ff"));
		            l3.setTargetProtocolAddress(arp.getSenderProtocolAddress());
		            l3.setTargetHardwareAddress(arp.getSenderHardwareAddress());
		            l3.setOpCode(ArpOpcode.REPLY);
		            l3.setHardwareAddressLength((byte) 0x06);
		            l3.setProtocolAddressLength((byte) 0x04);
	            	

	            	l2.setPayload(l3);
	            	
	            	byte[] serializedData = l2.serialize();
	            	
	            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
	            			.setData(serializedData)
	            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
	            			.setInPort(OFPort.CONTROLLER)
	            			.build();
	            	
	            	sw.write(po);
	            	return Command.STOP;
                }

	        } else {
	            /* Unhandled ethertype */
	        }
	        
	        break;
	    default:
	        break;
	    }
	    
	    return Command.CONTINUE;
	}

}

