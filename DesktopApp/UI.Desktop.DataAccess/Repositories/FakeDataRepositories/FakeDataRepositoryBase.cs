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

namespace UFart.Desktop.DataAccess.Repositories.FakeDataRepositories
{
    public class FakeDataRepositoryBase<TEntity>
        : IEntityRepositoryBase<TEntity> where TEntity : class
    {
        internal List<TEntity> fakeData;
        public FakeDataRepositoryBase(List<TEntity> fakeData)
        {
            this.fakeData = fakeData;
        }

        public void Create(TEntity item)
        {
            if (fakeData.FirstOrDefault(f => (f as EntityBase).ID == (item as EntityBase).ID) == null)
            {
                fakeData.Add(item);
                return;
            }
        }

        public TEntity Get(int id)
        {
            return fakeData.Select(f => f as EntityBase).FirstOrDefault(f => f.ID == id) as TEntity;
        }

        public IEnumerable<TEntity> GetAll()
        {
            return fakeData;
        }

        public void Remove(TEntity item)
        {
            fakeData.Remove(item);
        }

        public void Update(TEntity item)
        {
            var toUpdateItem = Get((item as EntityBase).ID);
            if (toUpdateItem != null)
            {
                Mapper.Map(item, toUpdateItem);
            }
        }
    }
}
