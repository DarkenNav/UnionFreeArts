using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories.Entities
{
    public class SitesRepository
        : AbstractRepositoryBase<Site>, IEntityRepositoryBase<Site>
    {
        public void Create(Site item) 
        {
            if (fakeData != null)
            {
                if (fakeData.FirstOrDefault(f => f.ID == item.ID) == null)
                {
                    fakeData.Add(item);
                    return;
                }
            }

            throw new NotImplementedException();
        }

        public Site Get(int id)
        {
            if (fakeData != null)
            {
                return fakeData.FirstOrDefault(f => f.ID == id);
            }

            throw new NotImplementedException();
        }

        public IEnumerable<Site> GetAll()
        {
            if (fakeData != null)
            {
                return fakeData;
            }
            throw new NotImplementedException();
        }

        public void Remove(Site item)
        {
            if (fakeData != null)
            {
                fakeData.Remove(item);
                return;
            }
            throw new NotImplementedException();
        }

        public void Update(Site item)
        {
            if (fakeData != null)
            {
                var toUpdateItem = Get(item.ID);
                if (toUpdateItem != null)
                {
                    Mapper.Map(item, toUpdateItem);
                }
                return;
            }
            throw new NotImplementedException();
        }
    }
}
