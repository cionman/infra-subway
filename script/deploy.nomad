job "app" {
  datacenters = ["dc1"]
  type = "service"

  group "app" {
    count = 3
    network {
         port "was" { to = 8080 }
        }
    update {
      max_parallel     = 1
      canary = 3
      min_healthy_time = "30s"
      healthy_deadline = "1m"
      auto_revert      = true
      auto_promote     = true
    }

    task "app" {
      driver = "docker"

      config {
        image = "cionman/infra-subway:1.1"
        ports = ["was"]
      }

      resources {
        cpu    = 500 # 500 MHz
        memory = 250 # 256MB

      }

      service {
        name = "app"
        tags = ["app"]
        port = "was"

        check {
          name     = "alive"
          type     = "tcp"
          interval = "10s"
          timeout  = "2s"
        }
      }
    }
  }
}