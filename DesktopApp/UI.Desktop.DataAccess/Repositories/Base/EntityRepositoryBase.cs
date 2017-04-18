using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity.Base;

namespace UFart.Desktop.DataAccess.Repositories.Base
{
    public class EntityRepositoryBase<TEntity>
        : IEntityRepositoryBase<TEntity> where TEntity : class
    {
        List<TEntity> fakeData;
        public EntityRepositoryBase()
        {

        }

        public EntityRepositoryBase(List<TEntity> fakeData)
        {
            this.fakeData = fakeData;
        }

        public void Create(TEntity item)
        {
            if (fakeData != null)
            {
                if (fakeData.FirstOrDefault(f => (f as EntityBase).ID == (item as EntityBase).ID) == null)
                {
                    fakeData.Add(item);
                    return;
                }
            }

            throw new NotImplementedException();
        }

        public TEntity Get(int id)
        {
            if (fakeData != null)
            {
                return fakeData.Select(f => f as EntityBase).FirstOrDefault(f => f.ID == id) as TEntity;
            }
            throw new NotImplementedException();

        }

        public IEnumerable<TEntity> GetAll()
        {
            if (fakeData != null)
            {
                return fakeData;
            }
            throw new NotImplementedException();
        }

        public void Remove(TEntity item)
        {
            if (fakeData != null)
            {
                fakeData.Remove(item);
                return;
            }
            throw new NotImplementedException();
        }

        public void Update(TEntity item)
        {
            if (fakeData != null)
            {
                var toUpdateItem = Get((item as EntityBase).ID);
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
