package ru.unionfreearts.webservice.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Rank;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.Repositories;
import ru.unionfreearts.webservice.repository.specifications.hibernate.HSAllRanks;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stat")
public class StatController {
    private IRepository<Rank> repository = Repositories.getRankFakeRepository();

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getEverydayStatistic(
            @RequestParam(value = "personId") Long personId,
            @RequestParam(value = "siteId") Long siteId,
            @RequestParam(value = "startDate") Long startDate,
            @RequestParam(value = "finishDate") Long finishDate) {
        List<Rank> ranks = repository.query(new HSAllRanks());
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Rank rank : ranks) {
            JSONObject dailyRank = new JSONObject();
            dailyRank.put("date", rank.getPage().getLastDateTime().getTime());
            dailyRank.put("rank", rank.getRank());
            jsonObjects.add(dailyRank);
        }
        if (ranks != null) {
            return new ResponseEntity<>(jsonObjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/total/{siteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getGeneralStatistic(@PathVariable Long siteId) {
        List<Rank> ranks = repository.query(new HSAllRanks());
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Rank rank : ranks) {
            JSONObject totalRank = new JSONObject();
            totalRank.put("name", rank.getPerson().getName());
            totalRank.put("rank", rank.getRank());
            jsonObjects.add(totalRank);
        }
        if (ranks != null) {
            return new ResponseEntity<>(jsonObjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
