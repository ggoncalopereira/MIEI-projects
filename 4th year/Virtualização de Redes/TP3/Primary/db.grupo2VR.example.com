;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	dns1.grupo2VR.example.com. admin.grupo2VR.example.com (
			      3		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
;
; name servers - NS records
	IN	NS	dns1.grupo2VR.example.com.
	IN	NS	dns2.grupo2VR.example.com.
; name servers - A records
dns1.grupo2VR.example.com.		IN	A	10.0.0.5
dns2.grupo2VR.example.com.		IN	A	10.0.0.6
; 10.0.0.0 - A records
cl1.grupo2VR.example.com.		IN	A	10.0.0.1
cl2.grupo2VR.example.com.		IN	A	10.0.0.2
