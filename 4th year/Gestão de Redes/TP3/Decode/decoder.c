#include "SimpleSyntax.h"
#include "ObjectSyntax.h"
#include "ObjectName.h"
#include "VarBind.h"
#include "VarBindList.h"
#include "SetRequest-PDU.h"
#include "GetRequest-PDU.h"
#include "PDUs.h"
#include "ANY.h"
#include "OCTET_STRING.h"
#include "Message.h"
#include <stdio.h>

//make -f decoder.mk

int main(int argc, char* argv[]){
	
	if(argv[1] == NULL){
		printf("No input file\n");
		return 0;
	}
	else{

		FILE *file;
		uint8_t * buffer;
		size_t buffer_size;

		file = fopen(argv[1], "rb");
		if (!file){
			perror("Unable to open file");
			exit(EXIT_FAILURE);
		}
	
		fseek(file, 0, SEEK_END);
		buffer_size = ftell(file);
		fseek(file, 0, SEEK_SET);

	
		buffer=(uint8_t *)malloc(buffer_size+1);
		if (!buffer){
			fprintf(stderr, "Memory error!");
        	fclose(file);
			exit(EXIT_FAILURE);
		}

		fread(buffer, buffer_size, 1, file);
		fclose(file);

		//Passo 1
		Message_t* message = 0;
		asn_dec_rval_t rval = asn_decode(0, ATS_BER, &asn_DEF_Message, (void **)&message, buffer, buffer_size);
	
		//Passo 2
		PDUs_t* pdu = 0;
		asn_dec_rval_t rval2 = asn_decode(0, ATS_BER, &asn_DEF_PDUs,(void **)&pdu, message->data.buf, message->data.size);
		VarBindList_t var_bindings;
		char *command;
		switch(pdu->present){
			case(PDUs_PR_get_request):
					command = "get";
					GetRequest_PDU_t getRequestPDU = pdu->choice.get_request;
					printf("Request id: %ld\n",getRequestPDU.request_id);
					printf("Error Index: %ld\n",getRequestPDU.error_index);
					printf("Error Status: %ld\n", getRequestPDU.error_status);
					var_bindings = pdu->choice.get_request.variable_bindings;
				break;
			case(PDUs_PR_get_next_request):
					command = "getnext";
					GetNextRequest_PDU_t getNextRequestPDU = pdu->choice.get_next_request;
					printf("Request id: %ld\n",getNextRequestPDU.request_id);
					printf("Error Index: %ld\n",getNextRequestPDU.error_index);
					printf("Error Status: %ld\n", getNextRequestPDU.error_status);
					var_bindings = pdu->choice.get_next_request.variable_bindings;
				break;
			case(PDUs_PR_get_bulk_request):
					command = "bulkget";
					GetBulkRequest_PDU_t getBulkRequestPDU = pdu->choice.get_bulk_request;
					printf("Request id: %ld\n",getBulkRequestPDU.request_id);
					printf("Non Repeaters: %ld\n",getBulkRequestPDU.non_repeaters);
					printf("Max Repetitions: %ld\n", getBulkRequestPDU.max_repetitions);
					var_bindings = pdu->choice.get_bulk_request.variable_bindings;
				break;
			case(PDUs_PR_response):
					command = "response";
					Response_PDU_t responsePDU = pdu->choice.response;
					printf("Request id: %ld\n",responsePDU.request_id);
					printf("Error Index: %ld\n",responsePDU.error_index);
					printf("Error Status: %ld\n", responsePDU.error_status);
					var_bindings = pdu->choice.response.variable_bindings;
				break;
			case(PDUs_PR_set_request):
					command = "set";
					SetRequest_PDU_t setRequestPDU = pdu->choice.set_request; 
 					printf("Request id: %ld\n",setRequestPDU.request_id);
					printf("Error Index: %ld\n",setRequestPDU.error_index);
					printf("Error Status: %ld\n",setRequestPDU.error_status);
					var_bindings = pdu->choice.set_request.variable_bindings;
				break;
			case(PDUs_PR_inform_request):
					command = "inform";
					InformRequest_PDU_t informRequestPDU = pdu->choice.set_request; 
 					printf("Request id: %ld\n",informRequestPDU.request_id);
					printf("Error Index: %ld\n",informRequestPDU.error_index);
					printf("Error Status: %ld\n",informRequestPDU.error_status);
					var_bindings = pdu->choice.inform_request.variable_bindings;
				break;
			case(PDUs_PR_snmpV2_trap):
					command = "trapd";
					SNMPv2_Trap_PDU_t snmpV2_trapPDU = pdu->choice.snmpV2_trap; 
 					printf("Request id: %ld\n",snmpV2_trapPDU.request_id);
					printf("Error Index: %ld\n",snmpV2_trapPDU.error_index);
					printf("Error Status: %ld\n",snmpV2_trapPDU.error_status);
					var_bindings = pdu->choice.snmpV2_trap.variable_bindings;
				break;
			case(PDUs_PR_report):
					command = "report";
					Report_PDU_t reportPDU = pdu->choice.report; 
 					printf("Request id: %ld\n",reportPDU.request_id);
					printf("Error Index: %ld\n",reportPDU.error_index);
					printf("Error Status: %ld\n",reportPDU.error_status);
					var_bindings = pdu->choice.report.variable_bindings;
				break;
			default:
				printf("Command not specified\n");
				break;
		}

		//Passo 3
		int var_list_size = var_bindings.list.count;
		VarBind_t* var_bind = var_bindings.list.array[0];
		
		ObjectName_t object_name = var_bind->name;

		int value;
		char type; 
		ObjectSyntax_t object_syntax = var_bind->choice.choice.value;
		switch(object_syntax.present){
			//SimpleSyntax case
			case(ObjectSyntax_PR_simple):{
				SimpleSyntax_t simple = object_syntax.choice.simple;
				switch(simple.present){
					case(SimpleSyntax_PR_integer_value):{
						value = simple.choice.integer_value;
						type = 'i';
					break;
					}
					case(SimpleSyntax_PR_string_value):{
						type = 'x';
					break;
					}
					case(SimpleSyntax_PR_objectID_value):{
						type = 'o';
					break;
					}
					default:
						printf("Field present empty\n");
						break;
				}
			break;
			}	
			//ApplicationSyntax case
			case(ObjectSyntax_PR_application_wide):{
				printf("ObjectSyntax Syntax found\n");
				ApplicationSyntax_t application = object_syntax.choice.application_wide;		
				switch(application.present){
					case(ApplicationSyntax_PR_NOTHING):{
						type = ' ';
						printf("Empty\n");
					break;
					}
					case(ApplicationSyntax_PR_ipAddress_value):{
						OCTET_STRING_t ipAddress_value = application.choice.ipAddress_value;
						type = 'a';
						printf("IP: %s \n", ipAddress_value.buf);
					break;
					}
					case(ApplicationSyntax_PR_counter_value):{
						//type = '';
						unsigned long counter_value = application.choice.counter_value;
						printf("Counter32: %ld \n", counter_value);	
					break;
					}
					case(ApplicationSyntax_PR_timeticks_value):{
						type = 't';
						unsigned long timeticks_value = application.choice.timeticks_value;
						printf("TimeTicks: %ld \n", timeticks_value);
					break;
					}
					case(ApplicationSyntax_PR_arbitrary_value):{
						OCTET_STRING_t arbitrary_value = application.choice.arbitrary_value;
						printf("Arbitrary Value: %s \n", arbitrary_value.buf);
					break;
					}
					case(ApplicationSyntax_PR_big_counter_value):{
						printf("Big Counter Value Found\n");
						//INTEGER_t big_counter_value = application.choice.big_counter_value;
						//printf("Counter64: %ld \n", big_counter_value.nat_value);
					break;
					}
					case(ApplicationSyntax_PR_unsigned_integer_value):{
						type = 'u';
						unsigned long unsigned_integer_value =  application.choice.unsigned_integer_value;
						printf("Unsigned Int %ld \n", unsigned_integer_value);
					break;
					}
					default:
						printf("Field present empty\n");
						break;
				}
			break;
			}
			default: 
				printf("No syntax found\n");
				break; 
		}

		printf("snmp%s -v %ld -c %s ",command, message->version,message->community.buf);
		int i;
		for(i = 0; i<object_name.size;i++){
			printf("%d",object_name.buf[i]);
			if(i != object_name.size - 1){
				printf(".");
			}	
		}
		if(strcmp(command,"set") == 0){
			printf(" OID %c %d\n",type, value);
		}else{
			printf(" OID\n");
		}
		printf("\n");

		//Debug
		FILE *file_output_descriptor;
		file_output_descriptor = fopen("debugD.xml","w");
		if(file_output_descriptor == NULL){
			perror("debug.xml failed");
			exit(EXIT_FAILURE);
		}
		xer_fprint(file_output_descriptor, &asn_DEF_Message, message);
		fclose(file_output_descriptor);

		free(buffer);
	}	
	return 0;

}