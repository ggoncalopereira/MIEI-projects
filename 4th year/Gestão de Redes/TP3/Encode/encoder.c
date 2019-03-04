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

//Para compilar, basta executar 'make -f encoder.mk'

//Passo 1.1.1 para simple syntax do tipo vazio
SimpleSyntax_t * createSimpleSyntaxEmpty(){

	SimpleSyntax_t *simple;
	simple = calloc(1, sizeof(SimpleSyntax_t));
	simple->present = SimpleSyntax_PR_NOTHING;
	return simple;

}

//Passo 1.1.2 para simple syntax do tipo integer_value
SimpleSyntax_t * createSimpleSyntaxInt(long integer_value){

	SimpleSyntax_t *simple;
	simple = calloc(1, sizeof(SimpleSyntax_t));
	simple->present = SimpleSyntax_PR_integer_value;
	simple->choice.integer_value = integer_value;
	return simple;

}

//Passo 1.1.3 para simple syntax do tipo string_value
SimpleSyntax_t * createSimpleSyntaxOctet(OCTET_STRING_t string_value){

	SimpleSyntax_t *simple;
	simple = calloc(1, sizeof(SimpleSyntax_t));
	simple->present = SimpleSyntax_PR_string_value;
	simple->choice.string_value = string_value;
	return simple;

}

//Passo 1.1.4 para simple syntax do tipo objectID_value
SimpleSyntax_t * createSimpleSyntaxObject(OBJECT_IDENTIFIER_t objectID_value){

	SimpleSyntax_t *simple;
	simple = calloc(1, sizeof(SimpleSyntax_t));
	simple->present = SimpleSyntax_PR_objectID_value;
	simple->choice.objectID_value = objectID_value;
	return simple;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxEmpty(){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_NOTHING;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxIP(IpAddress_t ipAddress_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_ipAddress_value;
	application->choice.ipAddress_value = ipAddress_value;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxCounter32(Counter32_t counter_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_counter_value;
	application->choice.counter_value = counter_value;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxTimeticks(TimeTicks_t timeticks_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_timeticks_value;
	application->choice.timeticks_value = timeticks_value;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxOpaque(Opaque_t arbitrary_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_arbitrary_value;
	application->choice.arbitrary_value = arbitrary_value;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxCounter64(Counter64_t big_counter_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_big_counter_value;
	application->choice.big_counter_value = big_counter_value;
	return application;

}

//Passo 1.2.1 para application syntax do tipo vazio
ApplicationSyntax_t * createApplicationSyntaxUnsigned(Unsigned32_t unsigned_integer_value){

	ApplicationSyntax_t *application;
	application = calloc(1, sizeof(ApplicationSyntax_t));
	application->present = ApplicationSyntax_PR_unsigned_integer_value;
	application->choice.unsigned_integer_value = unsigned_integer_value;
	return application;

}

//Passo 2.1 para object simple
ObjectSyntax_t * createObjectSyntaxSimple(SimpleSyntax_t *simple){

	ObjectSyntax_t *object_syntax;
	object_syntax = calloc(1, sizeof(ObjectSyntax_t));
	object_syntax->present = ObjectSyntax_PR_simple;
	object_syntax->choice.simple = *simple;
	return object_syntax;

}

//Passo 2.2 para object simple
ObjectSyntax_t * createObjectSyntaxApplication(ApplicationSyntax_t *application){

	ObjectSyntax_t *object_syntax;
	object_syntax = calloc(1, sizeof(ObjectSyntax_t));
	object_syntax->present = ObjectSyntax_PR_application_wide;
	object_syntax->choice.application_wide = *application;
	return object_syntax;

}

//Passo 3
ObjectName_t * createObjectName(uint8_t* name,size_t name_size){
	int i;
	ObjectName_t *object_name;
	object_name = calloc(1, sizeof(ObjectName_t));
	object_name->buf = name;
	object_name->size = name_size;
	//printf("\n%ld\n", name_size);
	return object_name;

}

//Passo 4
VarBind_t * createVarBind(ObjectName_t *object_name, ObjectSyntax_t *object_syntax){

	VarBind_t * var_bind;
	var_bind = calloc(1, sizeof(VarBind_t));
	var_bind->name = *object_name;
	var_bind->choice.present = choice_PR_value;
	var_bind->choice.choice.value = *object_syntax;
	return var_bind;

}

//Passo 5
VarBindList_t * createVarBindList(VarBind_t * var_bind){
	
	VarBindList_t * varlist;
	varlist = calloc(1, sizeof(VarBindList_t));
	int r = ASN_SEQUENCE_ADD(&varlist->list, var_bind);
	return varlist;

}

//Passo 6.1 para getRequest
GetRequest_PDU_t * createGetRequestPDU(int requestID, VarBindList_t* varlist){

	GetRequest_PDU_t * getRequestPDU;
	getRequestPDU = calloc(1, sizeof(GetRequest_PDU_t));
	getRequestPDU->request_id = requestID;
	getRequestPDU->error_index = 0;
	getRequestPDU->error_status = 0;
	getRequestPDU->variable_bindings = *varlist;
	return getRequestPDU;

}

//Passo 6.2 para getNextRequest
GetNextRequest_PDU_t * createGetNextRequestPDU(int requestID, VarBindList_t* varlist){

	GetNextRequest_PDU_t * getNextRequestPDU;
	getNextRequestPDU = calloc(1, sizeof(GetNextRequest_PDU_t));
	getNextRequestPDU->request_id = requestID;
	getNextRequestPDU->error_index = 0;
	getNextRequestPDU->error_status = 0;
	getNextRequestPDU->variable_bindings = *varlist;
	return getNextRequestPDU;

}

//Passo 6.3 para getBulkRequest
GetBulkRequest_PDU_t * createGetBulkRequestPDU(int requestID, VarBindList_t* varlist){

	GetBulkRequest_PDU_t * getBulkRequestPDU;
	getBulkRequestPDU = calloc(1, sizeof(GetBulkRequest_PDU_t));
	getBulkRequestPDU->request_id = requestID;
	getBulkRequestPDU->non_repeaters = 0;
	getBulkRequestPDU->max_repetitions = 0;
	getBulkRequestPDU->variable_bindings = *varlist;
	return getBulkRequestPDU;

}

//Passo 6.4 para response
Response_PDU_t * createResponsePDU(int requestID, VarBindList_t* varlist){

	Response_PDU_t * responsePDU;
	responsePDU = calloc(1, sizeof(Response_PDU_t));
	responsePDU->request_id = requestID;
	responsePDU->error_index = 0;
	responsePDU->error_status = 0;
	responsePDU->variable_bindings = *varlist;
	return responsePDU;

}

//Passo 6.5 para setRequest
SetRequest_PDU_t * createSetRequestPDU(int requestID, VarBindList_t* varlist){

	SetRequest_PDU_t * setRequestPDU;
	setRequestPDU = calloc(1, sizeof(GetRequest_PDU_t));
	setRequestPDU->request_id = requestID;
	setRequestPDU->error_index = 0;
	setRequestPDU->error_status = 0;
	setRequestPDU->variable_bindings = *varlist;
	return setRequestPDU;

}

//Passo 6.6 para informRequest
InformRequest_PDU_t * createInformRequestPDU(int requestID, VarBindList_t* varlist){

	InformRequest_PDU_t * informRequestPDU;
	informRequestPDU = calloc(1, sizeof(InformRequest_PDU_t));
	informRequestPDU->request_id = requestID;
	informRequestPDU->error_index = 0;
	informRequestPDU->error_status = 0;
	informRequestPDU->variable_bindings = *varlist;
	return informRequestPDU;

}

//Passo 6.7 para trap
SNMPv2_Trap_PDU_t * createTrapPDU(int requestID, VarBindList_t* varlist){

	SNMPv2_Trap_PDU_t * snmpV2_trapPDU;
	snmpV2_trapPDU = calloc(1, sizeof(SNMPv2_Trap_PDU_t));
	snmpV2_trapPDU->request_id = requestID;
	snmpV2_trapPDU->error_index = 0;
	snmpV2_trapPDU->error_status = 0;
	snmpV2_trapPDU->variable_bindings = *varlist;
	return snmpV2_trapPDU;

}

//Passo 6.8 para report
Report_PDU_t * createReportPDU(int requestID, VarBindList_t* varlist){

	Report_PDU_t * reportPDU;
	reportPDU = calloc(1, sizeof(Report_PDU_t));
	reportPDU->request_id = requestID;
	reportPDU->error_index = 0;
	reportPDU->error_status = 0;
	reportPDU->variable_bindings = *varlist;
	return reportPDU;

}


//Passo 7.1 para get-request
PDUs_t * createPDUget(GetRequest_PDU_t* get_requestPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_get_request;
	pdus->choice.get_request = *get_requestPDU;
	return pdus;

}

//Passo 7.2 para get-next-request
PDUs_t * createPDUgetnext(GetNextRequest_PDU_t* get_next_requestPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_get_next_request;
	pdus->choice.get_next_request = *get_next_requestPDU;
	return pdus;

}

//Passo 7.3 para get-bulk-request
PDUs_t * createPDUgetbulk(GetBulkRequest_PDU_t* get_bulk_requestPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_get_bulk_request;
	pdus->choice.get_bulk_request = *get_bulk_requestPDU;
	return pdus;

}

//Passo 7.4 para response
PDUs_t * createPDUresponse(Response_PDU_t* responsePDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_response;
	pdus->choice.response = *responsePDU;
	return pdus;

}

//Passo 7.5 para set-request 
PDUs_t * createPDUset(SetRequest_PDU_t* set_requestPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_set_request;
	pdus->choice.set_request = *set_requestPDU;
	return pdus;

}

//Passo 7.6 para inform-request
PDUs_t * createPDUinform(InformRequest_PDU_t* informRequestPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_inform_request;
	pdus->choice.inform_request = *informRequestPDU;
	return pdus;

}

//Passo 7.7 para snmpV2-trap
PDUs_t * createPDUtrap(SNMPv2_Trap_PDU_t* snmpV2_trapPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_snmpV2_trap;
	pdus->choice.snmpV2_trap = *snmpV2_trapPDU;
	return pdus;

}

//Passo 7.8 para report
PDUs_t * createPDUreport(Report_PDU_t* reportPDU){

	PDUs_t * pdus;
	pdus = calloc(1, sizeof(PDUs_t));
	pdus->present = PDUs_PR_report;
	pdus->choice.report = *reportPDU;
	return pdus;

}



//Passo 8, não importa a PDU que recebe
asn_enc_rval_t encode(uint8_t *buffer, size_t buffer_size, PDUs_t *pdus){

	return (asn_encode_to_buffer(0, ATS_BER, &asn_DEF_PDUs, pdus, buffer, buffer_size) );

}


//Passo 9, não importa a PDU que recebe
ANY_t * createANY(uint8_t *buffer, asn_enc_rval_t ret){

	ANY_t * data;
	data = calloc(1, sizeof(ANY_t));
	data->buf = buffer;
	data->size = ret.encoded;
	if(ret.encoded == -1){
		printf("Not encoded\n");
	}
	return data;

}

//Passo 10, não importa a PDU que recebe
Message_t * createMessage(long version, OCTET_STRING_t community, ANY_t* data){
	
	Message_t * message;
	message = calloc(1, sizeof(Message_t));
	message->version = version;
	message->community = community;
	message->data = *data;
	return message;

}

//Passo 11, não importa a PDU que recebe
asn_enc_rval_t finalEncode(uint8_t *buffer_final, size_t buffer_final_size, Message_t* message){

	return asn_encode_to_buffer(0, ATS_BER, &asn_DEF_Message, message, buffer_final, buffer_final_size);

}


int main(int argc, char* argv[]){
	SimpleSyntax_t* simple;
	ObjectSyntax_t* object_syntax;
	ObjectName_t * object_name;
	VarBind_t* var_bind;
	VarBindList_t* varlist;
	PDUs_t* pdus;
	ApplicationSyntax_t* application;

	if(argc <= 1){
		printf("Supported syntax:\n");
		printf("snmp<command> -v <version_number> -c <community_string> <hostip> <OID>\n");
		printf("Maximum arguments supported currently at 7\n");
		exit(EXIT_FAILURE);
	}

	char* commands[8] = {"snmpget", "snmpgetnext", "snmpbulkget", "snmpresponse", "snmpset", "snmptrapd", "snmpinform", "snmpreport"};

	int found = 0;
	int command_number = 0;
	int simple_exists = 0;
	//Search for the specified command
	while(!found){

		if(strcmp(argv[1],commands[0])==0){ //snmpget argc = 8
			found = 1;
			command_number = 1;
		}

		if(strcmp(argv[1],commands[1])==0){ //snmpgetnext argc = 8
			found = 1;
			command_number = 2;
		}

		if(strcmp(argv[1],commands[2])==0){ //snmpbulkget argc = 8
			found = 1;
			command_number = 3;
		}

		if(strcmp(argv[1],commands[3])==0){ //snmpresponse
			found = 1;
			command_number = 4;
		}

		if(strcmp(argv[1],commands[4])==0){ //snmpset argc = 10
			found = 1;
			command_number = 5;
		}

		if(strcmp(argv[1],commands[5])==0){ //snmpinform
			found = 1;
			command_number = 6;
		}

		if(strcmp(argv[1],commands[6])==0){ //snmptrapd
			found = 1;
			command_number = 7;
		}

		if(strcmp(argv[1],commands[7])==0){ //snmpreport
			found = 1;
			command_number = 8;
		}

		if(found == 0){
			perror("Command not valid (it either doesn't exist, or it's not fully lowercased)");
			exit(EXIT_FAILURE);
			break; //redundante?
		}
	}
	//Create the whole structures depending on the command provided
	switch(command_number){
		case 1:{ //snmpget
			if(argc != 8){
				printf("Please make sure you've written the snmpget command correctly\n");
				printf("The current snmpget supports the following syntax:\n");
				printf("snmpget -v <version> -c <community_string> <hostip> <OID>\n");
				printf("Example: snmpget -v 2 -c public 127.0.0.1 system.sysUpTime.0\n");
				exit(EXIT_FAILURE);
			}

			simple = createSimpleSyntaxInt(0);
				if(simple == NULL){
				perror("SimpleSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			object_syntax = createObjectSyntaxSimple(simple);
				if(object_syntax == NULL){
				perror("ObjectSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			
			//Parse the IP into 4 small sub-strings
			char* ip = argv[6];
			uint8_t name[4];
			int i = 0;
			char * tmp;
  			tmp = strtok (ip,".");
  			while (tmp != NULL){
    			name[i] = atoi(tmp);
    			tmp = strtok (NULL, ".");
    			i++;
  			}
  			//Create the object with the OID
			size_t name_size = sizeof(name);
			object_name = createObjectName(name,name_size);
			if(object_name == NULL){
				perror("ObjectName_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varBind
			var_bind = createVarBind(object_name,object_syntax);
			if(var_bind == NULL){
				perror("VarBind_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varList
			varlist = createVarBindList(var_bind);
			if(varlist == NULL){
				perror("VarBindList_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			
			//Create the respective request PDU
			GetRequest_PDU_t* getRequestPDU = createGetRequestPDU(1,varlist);
			if(getRequestPDU == NULL){
				perror("SetRequest_PDU_t calloc() failed");
				exit(EXIT_FAILURE);
			}		

			//Create the respective PDU
			pdus = createPDUget(getRequestPDU);
			if(pdus == NULL){
				perror("PDUs_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Finally end to encode into a message
			break;
		}

		case 2:{
			if(argc != 8){ //snmpgetnext
				printf("Please make sure you've written the snmpget command correctly\n");
				printf("The current snmpget supports the following syntax:\n");
				printf("snmpgetnext -v <version> -c <community_string> <hostip> <OID>\n");
				printf("Example: snmpgetnext -v 2 -c public 127.0.0.1 system.sysUpTime.0\n");
			}

			simple = createSimpleSyntaxInt(0);
				if(simple == NULL){
				perror("SimpleSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			object_syntax = createObjectSyntaxSimple(simple);
				if(object_syntax == NULL){
				perror("ObjectSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			
			//Parse the IP into 4 small sub-strings
			char* ip = argv[6];
			uint8_t name[4];
			int i = 0;
			char * tmp;
  			tmp = strtok (ip,".");
  			while (tmp != NULL){
    			name[i] = atoi(tmp);
    			tmp = strtok (NULL, ".");
    			i++;
  			}

  			//Create the object with the OID
			size_t name_size = sizeof(name);
			object_name = createObjectName(name,name_size);
			if(object_name == NULL){
				perror("ObjectName_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varBind
			var_bind = createVarBind(object_name,object_syntax);
			if(var_bind == NULL){
				perror("VarBind_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varList
			varlist = createVarBindList(var_bind);
			if(varlist == NULL){
				perror("VarBindList_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective request PDU
			GetNextRequest_PDU_t* getNextRequestPDU = createGetNextRequestPDU(1,varlist);
			if(getNextRequestPDU == NULL){
				perror("SetRequest_PDU_t calloc() failed");
				exit(EXIT_FAILURE);
			}		

			//Create the respective PDU
			pdus = createPDUgetnext(getNextRequestPDU);
			if(pdus == NULL){
				perror("PDUs_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			//Finally end to encode into message
			break;
		}
		
		case 3:{
			if(argc != 8){ //snmpbulkget
				printf("Please make sure you've written the snmpget command correctly\n");
				printf("The current snmpget supports the following syntax:\n");
				printf("snmpbulkget -v <version> -c <community_string> <hostip> <OID>\n");
				printf("Example: snmpbulkget -v 2 -c public 127.0.0.1 system.sysUpTime.0\n");
			}

			simple = createSimpleSyntaxInt(0);
				if(simple == NULL){
				perror("SimpleSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			object_syntax = createObjectSyntaxSimple(simple);
				if(object_syntax == NULL){
				perror("ObjectSyntax_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			
			//Parse the IP into 4 small sub-strings
			char* ip = argv[6];
			uint8_t name[4];
			int i = 0;
			char * tmp;
  			tmp = strtok (ip,".");
  			while (tmp != NULL){
    			name[i] = atoi(tmp);
    			tmp = strtok (NULL, ".");
    			i++;
  			}
  			
  			//Create the respective object name
			size_t name_size = sizeof(name);
			object_name = createObjectName(name,name_size);
			if(object_name == NULL){
				perror("ObjectName_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varBind
			var_bind = createVarBind(object_name,object_syntax);
			if(var_bind == NULL){
				perror("VarBind_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective varBind list
			varlist = createVarBindList(var_bind);
			if(varlist == NULL){
				perror("VarBindList_t calloc() failed");
				exit(EXIT_FAILURE);
			}

			//Create the respective request PDU
			GetBulkRequest_PDU_t* getBulkRequestPDU = createGetBulkRequestPDU(1,varlist);
			if(getBulkRequestPDU == NULL){
				perror("SetRequest_PDU_t calloc() failed");
				exit(EXIT_FAILURE);
			}		
			
			//Create the respective PDU
			pdus = createPDUgetbulk(getBulkRequestPDU);
			if(pdus == NULL){
				perror("PDUs_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			break;
			//Finally end to encode message
		}

		case 4:{
			printf("snmpresponse unsupported yet\n");
			exit(EXIT_FAILURE);
			break;
		}
		
		case 5:{ //snmpset
			if(argc != 10){
				printf("Please make sure you've written the snmpget command correctly\n");
				printf("The current snmpget supports the following syntax:\n");
				printf("snmpset -v <version> -c <community_string> <hostip> <OID> <TYPE> <TYPE_value>\n");
				printf("Example: snmpset -v 2 -c private 127.0.0.1 system.sysContact.0 s email@company.com\n");
			}
			//Discover the specified type_value and create                       
			//the structure according to it         							 
			if(strcmp(argv[8], "i")==0){ //Integer                               
				simple = createSimpleSyntaxInt(atoi(argv[9]));   
				if(simple == NULL){												 
					perror("SimpleSyntax_t calloc() failed");					 
					exit(EXIT_FAILURE);											 
				}																 
				simple_exists = 1;												 
			}
			/*
			if(strcmp(argv[8], "x")==0){ //Octet string
				OCTET_STRING_t new = (OCTET_STRING_t) argv[10];
				SimpleSyntax_t * simple = createSimpleSyntaxOctet(new);
				if(simple == NULL){
					perror("SimpleSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
				simple_exists = 1;
			}

			if(strcmp(argv[8], "o")==0){ //Object
				SimpleSyntax_t * simple = createSimpleSyntaxObject(argv[10]);
				if(simple == NULL){
					perror("SimpleSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
				simple_exists = 1;
			}
			
			if(strcmp(argv[8], "a")==0){ //IP Address
				ApplicationSyntax_t * application = createApplicationSyntaxIP(argv[10]);
				if(application == NULL){
					perror("ApplicationSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
			}
			*/

			if(strcmp(argv[8], "t")==0){ //Timetick
				application = createApplicationSyntaxTimeticks(atoi(argv[9]));
				if(simple == NULL){
					perror("SimpleSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
			}

			if(strcmp(argv[8], "u")==0){ //Unsigned
				application = createApplicationSyntaxUnsigned(atoi(argv[9]));
				if(simple == NULL){
					perror("SimpleSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
			}
			if(simple_exists == 1){ //Either a simple object
				object_syntax = createObjectSyntaxSimple(simple);
				if(object_syntax == NULL){
					perror("ObjectSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
			}
			else{ //Or an Application
				object_syntax = createObjectSyntaxApplication(application);
				if(object_syntax == NULL){
					perror("ObjectSyntax_t calloc() failed");
					exit(EXIT_FAILURE);
				}
			}

			//Parse the IP into 4 small sub-strings
			char* ip = argv[6];
			uint8_t name[4];
			int i = 0;
			char * tmp;
  			tmp = strtok (ip,".");
  			while (tmp != NULL){
    			name[i] = atoi(tmp);
    			tmp = strtok (NULL, ".");
    			i++;
  			}
			//Create the respective object
			size_t name_size = sizeof(name);
			object_name = createObjectName(name,name_size);
			if(object_name == NULL){
				perror("ObjectName_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			//Create the respective varBind
			var_bind = createVarBind(object_name,object_syntax);
			if(var_bind == NULL){
				perror("VarBind_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			//Create the respective varList
			varlist = createVarBindList(var_bind);
			if(varlist == NULL){
				perror("VarBindList_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			//Initialize the request PDU with the varList
			SetRequest_PDU_t* setRequestPDU = createSetRequestPDU(1,varlist);
			if(setRequestPDU == NULL){
				perror("SetRequest_PDU_t calloc() failed");
				exit(EXIT_FAILURE);
			}		
			//Create the respective PDU
			pdus = createPDUset(setRequestPDU);
			if(pdus == NULL){
				perror("PDUs_t calloc() failed");
				exit(EXIT_FAILURE);
			}
			//Finally end to encode message
			break;
		}
		
		case 6:{
			printf("snmptrapd unsupported yet\n");
			exit(EXIT_FAILURE);
			break;
		}

		case 7:{
			printf("snmpinform unsupported yet\n");
			exit(EXIT_FAILURE);
			break;
		}
		
		case 8:{
			printf("snmpreport unsupported yet\n");
			exit(EXIT_FAILURE);
			break;
		}
	}

	uint8_t buffer[1024];
	size_t buffer_size = sizeof(buffer);
	printf("First encode...");
	asn_enc_rval_t valor = encode(buffer,buffer_size,pdus);
	if(buffer == NULL){
		perror("Buffer with NULL value\n");
		exit(EXIT_FAILURE);
	}
	printf(" done\n");

	ANY_t * data = createANY(buffer,valor);
	if(data == NULL){
		perror("ANY_t calloc() failed");
		exit(EXIT_FAILURE);
	}



	long version = atoi(argv[3]); 
	OCTET_STRING_t oct_str; 
	oct_str.buf = strdup(argv[5]);
	oct_str.size = sizeof(oct_str.buf);
	Message_t * message = createMessage(version, oct_str ,data);
	if(message == NULL){
		perror("Message_t calloc() failed");
		exit(EXIT_FAILURE);
	}

	//Encoding message
	uint8_t buffer_final[1024];
	size_t buffer_final_size = sizeof(buffer_final);
	printf("Final encode...");
	asn_enc_rval_t valor2 = finalEncode(buffer_final, buffer_final_size, message);
	if(buffer == NULL){
		perror("Final Buffer with NULL value\n");
		exit(EXIT_FAILURE);
	}
	printf(" done\n");
	
	// Create binary file
	FILE *fp = fopen("encoded.bin","w");
	if(!fp){
		perror("encoded.bin not found");
		exit(EXIT_FAILURE);
	}
	else{
		fwrite(buffer_final, valor2.encoded , 1, fp);
		printf("Binary written successfully into encoded.bin\n");

	}
	fclose(fp);

	//Debug
	FILE *file_output_descriptor;
	file_output_descriptor = fopen("debugE.xml","w");
	if(file_output_descriptor == NULL){
		perror("debugE.xml failed");
		exit(EXIT_FAILURE);
	}
	xer_fprint(file_output_descriptor, &asn_DEF_Message, message);
	printf("XML written succesfully into debugE.xml\n");
	fclose(file_output_descriptor);

	printf("Success\n");

	return 0;
}