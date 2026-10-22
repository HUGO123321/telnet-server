# Úkol 3 - Server

[![UTB FAI Task](https://img.shields.io/badge/UTB_FAI-Task-yellow)](https://www.fai.utb.cz/)
[![Java](https://img.shields.io/badge/Java-007396.svg?logo=java&logoColor=white)](https://www.java.com/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?logo=gradle&logoColor=white)](https://gradle.org/)

|                                  |                                                   |
| -------------------------------- | ------------------------------------------------- |
| __Maximální počet bodů__         | 4 bodů                                            |
| __Způsob komunikace s aplikací__ | Parametry předané při spuštění + síťové připojení |
| __Způsob testování aplikace__    | Na základě odesílaných zpráv po síti              |

---

## 📝 Zadání úkolu 

Naprogramujte jednoduchý server, který bude schopný přijímat spojení od neomezeného počtu klientů. Server bude vykonávat funkci (tzv. "echo" funkce). To znamená, že zprávu, kterou obdrží od libovolného klienta, mu ji přepošle zpět. Pro obsluhu více klientů vytvořte jedno vlákno pro každé připojení. __Jako klienta využijte aplikaci telnet klienta, kterého jste implementovali již v předchozí úloze.__

### Požadavky:
* Server musí být schopen přijímat spojení od __neomezeného__ počtu klientů.
* Pro obsluhu každého klienta bude __samostatné__ vlákno. Implementace ve tříde ```ClientThread```.
* Klientovi server pošle zpět zprávu, kterou od něj přijme (Echo).
* Možnost omezení počtu vláken s využitím ThreadPoolExecutor.

```java
package utb.fai;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Implementation of processing incoming communication from the telnet client
    }
}
```

V hlavní třídě aplikace implementujte hlavní smyčku serveru. Tedy otevírání nových spojení mezi klientem a serverem. Využijte třídu ```ServerSocket```. Při spouštění aplikace budou aplikaci serveru předávány následující parametry.

### Struktura pole vstupních parametrů
1. __args[0]__ - Port, na kterém bude server naslouchat _(int)_
2. __args[1]__ - Maximální počet vláken. V tomto případě číslo bude i odpovídat maximálnímu počtu klientů, kteří budou moci být v jeden okamžik připojení.  _(int)_

```java
package utb.fai;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {
        int port = 12345;
        int max_threads = 10;    

        // Implement input parameter processing
        
        // Implementation of the main server loop
        ServerSocket serverSocket;
    }
}
```

>_**Poznámka:** Implementace a struktura kódu aplikace je libovolná a je zcela na vás, jak tento problém vyřešíte. Je však důležité, aby aplikace splňovala zadané požadavky._

---

## 🏆 Způsob hodnocení

Vaše implementace bude hodnocena na základě chování aplikace při testování různých scénářů. Automatizovaný testovací nástroj bude předávat vaší aplikaci různé parametry, včetně platných a neplatných, aby otestoval její chování za různých podmínek. V případě testování síťové komunikace mezi více klienty, testovací nástroj bude vytvářet virtuální klienty/servery za účelem ověření funkcionality.

Výsledné hodnocení bude záviset na celkovém počtu úspěšných testovacích případů. Počet bodů získaných z úlohy bude tedy záviset na celkové úspěšnosti při testování. Váš výsledný počet bodů bude určen následujícím vzorcem.

__VP__ = __MB__ * (__UT__ / __CPT__)

### Popis symbolů:

* __VP:__ Výsledný počet bodů.
* __MB:__ Maximální počet bodů pro danou úlohu.
* __UT:__ Počet úspěšných testovacích případů.
* __CPT:__ Celkový počet testovacích případů.

## ⚙️ Jak spustit automatizované hodnocení lokálně na svém počítači?

Automatizované hodnocení můžete spustit lokálně za účelem ověření funkčnosti vaší aplikace. K tomu slouží předpřipravený skript, který je dostupný v repozitáři tohoto úkolu. Výsledný report testování se bude nacházet v souboru ```test_report.html```.

###  Pro uživatele systému Linux:
Spusťte skript s názvem ```run_local_test.sh```.

### Pro uživatele systému Windows:
Spusťte skript s názvem ```run_local_test.bat```.

