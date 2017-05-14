using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories.WebApi
{
    public class PersonPageRankRepository
        : EntityRepositoryBase<PersonPageRank>, IPersonPageRankRepository
    {
        public PersonPageRankRepository()
            : base()
        {

        }

        public IEnumerable<PersonPageRank> GetBySite(int idSite)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<PersonPageRank> GetBy(
            int? idSite = null, 
            int? idPerson = null, 
            DateTime? fromDate = null, 
            DateTime? toDate = null, 
            int? skip = null, 
            int? take = null)
        {
            throw new NotImplementedException();
        }
    }
}
