using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UI.Desktop.DataAccess.Repositories.Base.Interfaces
{
    public interface IRepositoryBase
        : IDisposable
    {
        void Save();
    }
}
