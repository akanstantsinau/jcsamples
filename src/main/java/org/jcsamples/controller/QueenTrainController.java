package org.jcsamples.controller;

import org.jcsamples.model.BaseTrain;
import org.jcsamples.model.QueenTrain;
import org.jcsamples.service.BaseRailwayService;
import org.jcsamples.service.QueenRailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


/**
 * Base Railway implementation controller.
 */
@RestController
@RequestMapping("/base1")
public class QueenTrainController {

    @Autowired
    private QueenRailwayService railwayService;

    @RequestMapping("/status")
    public @ResponseBody
    Collection<QueenTrain> status() {
        return railwayService.getTrains();
    }

    @RequestMapping("/to_london")
    public @ResponseBody Collection<QueenTrain> toLondon(
            @RequestParam(value="queen", required=false, defaultValue = "false") Boolean queen) {
        railwayService.newTrainToLondon(queen);
        return railwayService.getTrains();
    }

    @RequestMapping("/to_paris")
    public @ResponseBody
    Collection<QueenTrain> toParis(@RequestParam(value="queen", required=false, defaultValue = "false") Boolean queen) {
        railwayService.newTrainToParis(queen);
        return railwayService.getTrains();
    }
}