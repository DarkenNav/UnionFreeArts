using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;
using UI.Desktop.DataAccess.DTO;

namespace UFart.Desktop.DataAccess.Mapping
{
    public static class MapperConfigurate
    {
        public static void Initialize(IMapperConfigurationExpression cfg)
        {
            cfg.CreateMap< PersonPageRank, RankOnDateDTO >()
               .ForMember(dto => dto.DateTick, conf => conf.MapFrom(ol => ol.Page.LastScanDate.Value.Ticks));

        }
    }
}
