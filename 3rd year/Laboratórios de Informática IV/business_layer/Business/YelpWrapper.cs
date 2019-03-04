using System;
using System.Collections.Generic;
using System.Linq;
using Yelp.Api;
using Yelp.Api.Models;

namespace Business
{

    public static class YelpWrapper
    {

        private const string APP_ID = "XFvWnBSi2xEMBVh6o2Ilkw";
        private const string APP_SECRET = "rnb2u1QbbNFKo3upXACx5UweMDtKAeJZ13VYDkUINsHTjmY35q79HetfBfCpDMtm";

        public static double ObterRating(double latitude, double longitude, string termo)
        {
            double ratingYelp = 0.0f;
           
            var client = new Client(APP_ID, APP_SECRET);
            SearchRequest search = new SearchRequest()
            {
                Term = termo,
                Latitude = latitude,
                Longitude = longitude,
                //Location = "Rua Dr. Alberto Macedo, 437, Porto, 4100-031",
                //search.Radius = 200;
                MaxResults = 10
            };

            var response = client.SearchBusinessesAllAsync(search);

            var llist = response.Result.Businesses.ToList().ToArray();
            var emptyList = new List<Tuple<string, float>>().Select(t => new { Nome = t.Item1, Rating = t.Item2 }).ToList();

            if (llist != null && llist.Length > 0)
            {
                foreach (var item in llist)
                {
                   
                    emptyList.Add(new { Nome = item.Name, Rating = item.Rating });
                  
                }
            }
            else
                Console.WriteLine("Sem resposta");
            foreach (var item in emptyList)
            {
              
                if (item.Nome.Equals(termo))
                {
                    ratingYelp = item.Rating;
                    break;
                }
                   
            }

            return ratingYelp;
        }

    }
}