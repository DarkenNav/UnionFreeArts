using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net;
using System.Text;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;

namespace UFart.Desktop.DataAccess.Repositories.WebApi
{
    public class WebApiRepositoryBase<TEntity>
        : IEntityRepositoryBase<TEntity> where TEntity : class
    {
        internal string url;
        internal WebClient client;

        public WebApiRepositoryBase(string url)
        {
            this.url = url;
            client = new WebClient();
            client.Encoding = Encoding.UTF8;
        }

        public void Create(TEntity item)
        {

            
        }

        public TEntity Get(int id)
        {
            var reply = string.Empty;
            try
            {
                reply = client.DownloadString($"{url}{id.ToString()}");
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            return JsonConvert.DeserializeObject<TEntity>(reply);
        }

        public IEnumerable<TEntity> GetAll()
        {
            var reply = string.Empty;
            try
            {
                reply = client.DownloadString(url);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            return JsonConvert.DeserializeObject<IEnumerable<TEntity>>(reply);

        }

        public void Remove(TEntity item)
        {
            
        }

        public void Update(TEntity item)
        {
           

        }
    }
}
