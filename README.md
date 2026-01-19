# Task 2 - Advanced Branching (Merge, Rebase, Squash, Cherry-pick)

## Branch Structure (Provided Repo)
- **main**: stable baseline branch.
- **dev**: integration branch where features are combined before release.
- **feature1**: quit option + play-again loop + improved feedback; will be updated from dev using **merge**, then merged into dev and deleted.
- **feature2**: max attempts + game-over logic; will be updated from dev using **rebase** (multiple conflict stages), then updated again after feature3 and merged into dev (do not delete).
- **feature3**: hint system; will be **squashed (4 -> 1)** with commit message:
  "Add hint system to show proximity after 3 attempts"
  then rebased onto dev and merged into dev (keep branch).
- **hotfix**: single urgent fix commit; will be **cherry-picked** onto main (NOT merged), then main merged into dev.
- **documentation**: this branch. Contains documentation + learning summary.

## What I will prove for grading
- Always verify history: `git log --oneline --graph --all --decorate`
- After conflict resolution, run: `./gradlew build` and `./gradlew test`
- Merge workflow: dev -> feature1 (merge), then feature1 -> dev, delete feature1
- Rebase workflow: feature2 rebase onto dev (resolve conflicts per stage) + tests passing
- Squash workflow: interactive rebase on feature3 (4 commits -> 1) with required message
- Cherry-pick workflow: hotfix commit onto main, then merge main into dev + tests passing
