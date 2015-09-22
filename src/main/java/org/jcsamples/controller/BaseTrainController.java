package org.jcsamples.controller;

import org.jcsamples.model.*;
import org.jcsamples.service.BaseRailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Base Railway implementation controller.
 */
@RestController
@RequestMapping("/base")
public class BaseTrainController {

    @Autowired
    private BaseRailwayService railwayService;

    @RequestMapping("/status")
    public @ResponseBody
    List<BaseTrain> status() {
        return railwayService.getTrains();
    }

    @RequestMapping("/to_london")
    public @ResponseBody List<BaseTrain> toLondon() {
        railwayService.newTrainToLondon();
        return railwayService.getTrains();
    }

    @RequestMapping("/to_paris")
    public @ResponseBody List<BaseTrain> toParis() {
        railwayService.newTrainToParis();
        return railwayService.getTrains();
    }
}