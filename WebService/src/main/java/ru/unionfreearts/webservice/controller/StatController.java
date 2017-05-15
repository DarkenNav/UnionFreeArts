package ru.unionfreearts.webservice.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.hibernate.AllRanksByPersonAndTime;
import ru.unionfreearts.webservice.dao.specifications.hibernate.AllRanksBySite;
import ru.unionfreearts.webservice.model.Rank;

import java.util.*;

@RestController
@RequestMapping(value = "/stat")
public class StatController {

    @Autowired
    private IRepository<Rank> rankRepository;

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getDailyStatistic(
            @RequestParam(value = "personId") Long personId,
            @RequestParam(value = "siteId") Long siteId,
            @RequestParam(value = "startDate") Long startDate,
            @RequestParam(value = "finishDate") Long finishDate) {
        List<Rank> ranks = rankRepository.query(new AllRanksByPersonAndTime(personId, siteId, new Date(startDate), new Date(finishDate)));
        List<JSONObject> jsonObjects = new ArrayList<>();
        Date nextDate = new Date(startDate);
        Date endDate = new Date(finishDate);
        do {
            JSONObject dailyRank = new JSONObject();
            for (Rank rank : ranks) {
                if (rank.getPage().getFoundDateTime().equals(nextDate) && !dailyRank.isEmpty()) {
                    dailyRank.replace("rank", (Integer) dailyRank.get("rank") + rank.getRank());
                } else if (dailyRank.isEmpty() && rank.getPage().getFoundDateTime().equals(nextDate)){
                    dailyRank.put("date", rank.getPage().getFoundDateTime().getTime());
                    dailyRank.put("rank", rank.getRank());
                }
            }
            if (!dailyRank.isEmpty())
                jsonObjects.add(dailyRank);
            nextDate.setTime(nextDate.getTime() + (1000 * 60 * 60 * 24));
        }
        while (nextDate.before(endDate));

        if (jsonObjects.size() > 0) {
            return new ResponseEntity<>(jsonObjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/total/{siteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getTotalStatistic(@PathVariable Long siteId) {
        List<Rank> ranks = rankRepository.query(new AllRanksBySite(siteId));
        List<JSONObject> jsonObjects = new ArrayList<>();
        Map<Long, Rank> rankMap = new HashMap<>();
        for (Rank rank : ranks) {
            Long personId = rank.getPerson().getId();
            if (rankMap.containsKey(personId)) {
                rankMap.get(personId).setRank(rankMap.get(personId).getRank()+rank.getRank());
            } else {
                rankMap.put(rank.getPerson().getId(), rank);
            }
        }
        for (Rank rank : rankMap.values()) {
            JSONObject totalRank = new JSONObject();
            totalRank.put("name", rank.getPerson().getName());
            totalRank.put("rank", rank.getRank());
            jsonObjects.add(totalRank);
        }
        if (jsonObjects.size() > 0) {
            return new ResponseEntity<>(jsonObjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
