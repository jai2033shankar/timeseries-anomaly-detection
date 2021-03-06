package com.orange.cloud.anomalydetection.predict;

import com.orange.cloud.anomalydetection.message.PredictionMessage;
import com.orange.cloud.anomalydetection.predict.domain.Observation;
import com.orange.cloud.anomalydetection.predict.domain.Prediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sebastien Bortolussi
 */
@RestController("/predict")
public class PredictionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionController.class);

    PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping
    PredictionMessage predict(@RequestParam String value) throws Exception {
        Observation observation = new Observation(new Double(value));
        final Prediction prediction = predictionService.predict(observation);
        LOGGER.debug("prediction : + " + prediction);
        return new PredictionMessage(String.valueOf(observation.getValue()), String.valueOf(prediction.getValue()));
    }

}
