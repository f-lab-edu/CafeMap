package com.flab.CafeMap.web.config;

import com.mysql.cj.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

/**
 * @Slf4j : 로깅을 위한 추상화 라이브러리
 * @Profile : 외부 설정 파일을 통해 런타임 환경을 설정할 수 있는 기능을 제공한다.
 */

@Slf4j
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() throws IOException {
        int port = isRedisRunning() ? findAvailablePort() : redisPort;
        log.info("current Redis port = {}", port);
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    //Embedded Redis가 현재 실행중인지 확인한다.
    private boolean isRedisRunning() throws IOException {
        return isRunning(executeGrepProcessCommand(redisPort));
    }

    //현재 pc와 서버에서 사용가능한 포트를 조회한다.
    public int findAvailablePort() throws IOException {
        for (int port = 10000; port <= 65535; port++) {
            Process process = executeGrepProcessCommand(port);
            if (!isRunning(process)) {
                return port;
            }
        }
        throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
    }

    //해당 포트를 사용 중인 프로세스를 확인하는 sh를 실행한다.
    private Process executeGrepProcessCommand(int port) throws IOException {
        String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
        String[] shell = {"/bin/sh", "-c", command};
        return Runtime.getRuntime().exec(shell);
    }

    //해당 프로세스가 현재 실행중인지 확인한다.
    private boolean isRunning(Process process) {
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
            new InputStreamReader(process.getInputStream()))) {
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return !StringUtils.isNullOrEmpty(sb.toString());
    }
}
