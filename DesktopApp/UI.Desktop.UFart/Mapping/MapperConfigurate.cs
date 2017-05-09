using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;
using UFart.Desktop.UI.DTO;

namespace UI.Desktop.UFart.Mapping
{
    public static class MapperConfigurate
    {
        internal static void Initialize(IMapperConfigurationExpression cfg)
        {
            cfg.CreateMap<Site, SiteDTO>();
            cfg.CreateMap<SiteDTO, Site>();

            cfg.CreateMap<Person, PersonDTO>();
            cfg.CreateMap<PersonDTO, Person>();
        }
    }
}
