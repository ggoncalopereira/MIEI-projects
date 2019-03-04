using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Yelp.Api;
using System.Threading;
using Yelp.Api.Models;
using System.Net.Http.Headers;


namespace DemoYelp
{
    class Program
    {

        private const string APP_ID = "XFvWnBSi2xEMBVh6o2Ilkw";
        private const string APP_SECRET = "rnb2u1QbbNFKo3upXACx5UweMDtKAeJZ13VYDkUINsHTjmY35q79HetfBfCpDMtm";
        //private const string APP_ID = "39ukJIrbqD1Pk5V16B5weA";
        //private const string APP_SECRET = "pDgLtCkYCRAnTpI9TP15xRMV4yeX75UMud05z7Cksm0KuCpw5qpQLMfVWzmOSBKC";
        static void Main(string[] args)
        {
            var client = new Client(APP_ID, APP_SECRET);
            // var results = ;
            Console.WriteLine(client);
            //41,166431427002  Long - 8,65548992156982
            //var response = await client.SearchBusinessesWithDeliveryAsync("mex", 37.786882, -122.399972).Result;
            //Console.WriteLine(response.Total);
            SearchRequest search = new SearchRequest();

            search.Term = "Tasca";
            search.Latitude = 41.166429;
            search.Longitude = -8.6576568;
            search.Location = "Rua Dr. Alberto Macedo, 437, Porto, 4100-031";
            //search.Radius = 200;
            search.MaxResults = 10;


            //var response = client.SearchBusinessesAllAsync(search);
            var response = client.SearchBusinessesWithDeliveryAsync("Tasca da Badalhoca", 41.166429, -8.6576568);
            Console.WriteLine(response.Result);
            /* foreach (var business in response.Businesses)
             {

             }*/


            var llist = response.Result.Businesses.ToList().ToArray();

            if (llist != null && llist.Length > 0)
            {
                foreach (var item in llist)
                {
                    Console.WriteLine("Nome {0} Rating {1}, ", item.Name,  item.Rating);
                }
            }
            else
                Console.WriteLine("Sem resposta");
            Console.ReadLine();
        }
    }
}
