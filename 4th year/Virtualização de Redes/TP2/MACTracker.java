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
	
	public void setFlag() {
	//	this.flag = !flag;
	}

	/*
	 * Overridden IOFMessageListener's receive() function.
	 */
	@Override
	public Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx) {
	    switch (msg.getType()) {
	    case PACKET_IN:
	        /* Retrieve the deserialized packet in message */
	        Ethernet eth = IFloodlightProviderService.bcStore.get(cntx, IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
	 
	        /* Various getters and setters are exposed in Ethernet */
	        logger.info("Source MAC Address: {} seen on switch: {}",
	        		eth.getSourceMACAddress().toString(),
	        		sw.getId().toString());
	        logger.info("Destination MAC Address: {} seen on switch: {}",
	        		eth.getDestinationMACAddress().toString(),
	        		sw.getId().toString());
	        // VlanVid vlanId = VlanVid.ofVlan(eth.getVlanID());
	 
	        /* 
	         * Check the ethertype of the Ethernet frame and retrieve the appropriate payload.
	         * Note the shallow equality check. EthType caches and reuses instances for valid types.
	         */
	        if (eth.getEtherType() == EthType.IPv4) {
	            /* We got an IPv4 packet; get the payload from Ethernet */
	            IPv4 ipv4 = (IPv4) eth.getPayload();
	             
	            /* Various getters and setters are exposed in IPv4 */
	            //byte[] ipOptions = ipv4.getOptions();
	            logger.info("Source IPv4 address: {} seen on switch: {}",
	            		ipv4.getSourceAddress().toString(),
		        		sw.getId().toString());
	            logger.info("Destination IPv4 address: {} seen on switch: {}",
	            		ipv4.getDestinationAddress().toString(),
		        		sw.getId().toString());
	             
	            if (ipv4.getProtocol() == IpProtocol.UDP) {
	                /* We got a UDP packet; get the payload from IPv4 */
	                UDP udp = (UDP) ipv4.getPayload();
	                if(ipv4.getDestinationAddress().toString().equals("10.0.0.250")) {
			            flag = !flag;
			            System.out.println("Generated flag is " + flag);
	                	
	                }
	  
	                /* Various getters and setters are exposed in UDP */
	                logger.info("Source UDP port: {} seen on switch: {}",
	                		udp.getSourcePort().toString(),
			        		sw.getId().toString());
	                logger.info("Destination UDP port: {} seen on switch: {}",
	                		udp.getDestinationPort().toString(),
			        		sw.getId().toString());
	                
	            }
	 
	        } else if (eth.getEtherType() == EthType.ARP) {
	            /* We got an ARP packet; get the payload from Ethernet */
	        	//IPv4 ipv4 = (IPv4) eth.getPayload();
	            ARP arp = (ARP) eth.getPayload();
	            //UDP udp = (UDP) ipv4.getPayload();
	            logger.info("ARP Source MAC address: {} seen on switch: {}",
	            		arp.getSenderHardwareAddress().toString(),
		        		sw.getId().toString());
	            logger.info("ARP Destination MAC address: {} seen on switch: {}",
	            		arp.getTargetHardwareAddress().toString(),
		        		sw.getId().toString());
	            
	            System.out.println("OPCode: " + arp.getOpCode());
	            
                if(arp.getTargetProtocolAddress().toString().equals("10.0.0.250")
                		&& arp.getOpCode().toString().equals("1")){
                	Ethernet l2 = (Ethernet) eth.clone();
		            //Ethernet l2 = new Ethernet();
                	ARP l3 = new ARP();
		            if(flag) {
		            	System.out.println("entrei true");
		            	l2.setSourceMACAddress(MacAddress.of("00:00:00:00:00:04"));	
		            	l3.setSenderHardwareAddress(MacAddress.of("00:00:00:00:00:04"));
		            }
		            else{
		            	System.out.println("entrei false");
		            	l2.setSourceMACAddress(MacAddress.of("00:00:00:00:00:05"));
		            	l3.setSenderHardwareAddress(MacAddress.of("00:00:00:00:00:05"));
		            }
		            l2.setDestinationMACAddress(arp.getSenderHardwareAddress());
		            l2.setEtherType(EthType.ARP);
		            
		            l3.setProtocolType(ARP.PROTO_TYPE_IP);
		            l3.setHardwareType(ARP.HW_TYPE_ETHERNET);
		            l3.setSenderProtocolAddress(IPv4Address.of("10.0.0.250"));
		            l3.setTargetProtocolAddress(arp.getSenderProtocolAddress());
		            l3.setTargetHardwareAddress(MacAddress.of("00:00:00:00:00:03"));
		            l3.setOpCode(ArpOpcode.REPLY);
		            l3.setHardwareAddressLength((byte) 0x06);
		            l3.setProtocolAddressLength((byte) 0x04);
	            	
	            	//UDP l4 = new UDP();
	            	//l4.setSourcePort(TransportPort.of(67));
	            	//l4.setDestinationPort(TransportPort.of(67));
	            	
	            	//Data l7 = new Data();
	            	//l7.setData(new byte[1000]);
	            	
	            	l2.setPayload(l3);
	            	//l3.setPayload(l4);
	            	//l4.setPayload(l7);
	            	
		            //if(l2.serialize().length == 0) System.out.println("Serialize is null!");
	            	
	            	System.out.println("serialized data " + l2.toString());
	            	System.out.println("received arp " + arp.toString());
	            	//System.out.println(udp.toString());
	            	System.out.println("final udp: " + l3.toString());
	            	
	            	byte[] serializedData = l2.serialize();
	            	
	            	OFPacketOut po = sw.getOFFactory().buildPacketOut()
	            			.setData(serializedData)
	            			.setActions(Collections.singletonList((OFAction) sw.getOFFactory().actions().output(OFPort.FLOOD, 0xffFFffFF)))
	            			.setInPort(OFPort.CONTROLLER)
	            			.build();
	            	
	            	sw.write(po);
	            	//System.out.println("Stop");
	            	return Command.STOP;
                }
	            //}
	            /* Various getters and setters are exposed in ARP */
	            //boolean gratuitous = arp.isGratuitous();
	 
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

