package main.service;

import lombok.AllArgsConstructor;
import main.data.response.InitResponse;
import main.data.response.SettingResponse;
import main.model.Setting;
import main.repository.PostRepository;
import main.repository.SettingRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitService {
    private final InitResponse initResponse;
    private final SettingRepository settingRepository;

    public InitResponse init() {
        return initResponse;
    }

    public SettingResponse settings() {
        SettingResponse settingResponse = new SettingResponse();
        Iterable<Setting> settingIterable = settingRepository.findAll();

        for (Setting setting : settingIterable) {
            boolean value = "YES".equals(setting.getValue());

            switch (setting.getCode()) {
                case "MULTIUSER_MODE":
                    settingResponse.setMultiuserMode(value);
                    break;
                case "POST_PREMODERATION":
                    settingResponse.setPostPremoderation(value);
                    break;
                case "STATISTICS_IS_PUBLIC":
                    settingResponse.setStatisticsPublic(value);
                    break;
            }
        }

        return settingResponse;
    }
}
