# RockPaperScissors
play rock paper scissors, play many games

---

# Rock-Paper-Scissors in COBOL

## Installation Requirements

### Fedora:
```bash
dnf install gnucobol
```

### Gentoo:
```bash
emerge --newuse --deep --ask --backtrack=20 dev-lang/gnucobol
```

## Getting Information

To check the version of `cobc`:
```bash
cobc -V
```

Ensure it's at least version 3.0 or higher.

## Compiling and Execution

To compile:
```bash
cobc -o rock-paper-scissors -x rock-paper-scissors.cob -O2
```

To execute:
```bash
chmod a+x rock-paper-scissors
./rock-paper-scissors
```

Example output:
```
Pick "rock", "paper", or "scissors"
paper
Computer chose rock
You win!
```

