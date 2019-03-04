using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;

namespace Yelp.Api
{
    internal static class DictionaryExtensions
    {
        internal static IEnumerable<KeyValuePair<T, S>> ToKeyValuePairList<T, S>(this Dictionary<T, S> dictionary)
        {
            var list = new List<KeyValuePair<T, S>>();
            foreach (var pair in dictionary)
                list.Add(pair);
            return list;
        }

        internal static string ToQueryString(this Dictionary<string, object> dictionary)
        {
            
            string querystring = string.Empty;
            List<string> parameters = new List<string>();

            if (dictionary == null)
                return querystring;

            foreach (var pair in dictionary.Where(w => w.Value != null))
            {
                if (IsNumber(pair.Value))
                {
                    double num;
                    bool isDouble = Double.TryParse(pair.Value.ToString(),  out num);
                    if (isDouble)
                    {
                        string tmp = num.ToString(CultureInfo.InvariantCulture);
                        parameters.Add(string.Join("=", pair.Key, Uri.EscapeUriString(tmp)));
                    }
                   
                }
                else
                {
                    parameters.Add(string.Join("=", pair.Key, Uri.EscapeUriString(pair.Value.ToString())));
                }

               
            }
              

            if (parameters.Count > 0)
                querystring = "?" + string.Join("&", parameters);

            return querystring;
        }

        public static bool IsNumber(this object value)
        {
            return value is sbyte
                    || value is byte
                    || value is short
                    || value is ushort
                    || value is int
                    || value is uint
                    || value is long
                    || value is ulong
                    || value is float
                    || value is double
                    || value is decimal;
        }
    }
}