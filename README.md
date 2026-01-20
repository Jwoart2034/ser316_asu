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

## Merge vs Rebase vs Squash vs Cherry-pick

### Merge
- Combines branches by creating a merge commit (unless fast-forward is possible).
- Preserves the true history of how branches diverged and rejoined.
- Useful when working on shared branches where history transparency matters.
- **Tradeoff:** Can clutter history if overused.

### Rebase
- Replays commits from one branch onto another base commit.
- Produces a cleaner, more linear commit history.
- Best used on local or private feature branches.
- **Tradeoff:** Rewrites commit history and should not be used on shared branches.

### Squash (Interactive Rebase)
- Combines multiple commits into a single commit.
- Ideal for cleaning up internal development commits before merging into `dev`.
- Keeps the main development history concise and readable.
- **Tradeoff:** Loses detailed step-by-step commit history.

### Cherry-pick
- Applies a single specific commit onto another branch.
- Commonly used for urgent hotfixes.
- Allows fixes to be applied to `main` without merging all of `dev`.
- **Tradeoff:** Can cause duplicate commits and conflicts if reused incorrectly.

---

## Observations from Feature Branch History

### feature1
- Used merge commits to integrate work.
- History clearly shows when feature work was merged back.
- Preserves full context but adds merge noise.

### feature2
- Used rebase initially to clean history.
- Required merging updated `dev` back into the feature as `dev` evolved.
- Demonstrates that rebasing helps early, but merges may still be necessary later.

### feature3
- Used squash to combine multiple development commits into one clean commit.
- Resulted in a single meaningful commit:
  - **Add hint system to show proximity after 3 attempts**
- Produced the cleanest integration into `dev`.

---

## When to Use Each Strategy in Real Projects

### Merge
- When multiple developers share a branch.
- When preserving integration context is important.

### Rebase
- When working alone on a feature branch.
- When preparing a branch for review before merging.

### Squash
- When internal commits are noisy or experimental.
- When `dev` history should reflect completed features only.

### Cherry-pick
- When applying a targeted hotfix to production (`main`).
- When a fix must be applied without pulling in unrelated changes.

---

## Key Takeaways
- Feature branches can be messy during development.
- Before merging into `dev`, history should be cleaned using rebase or squash.
- Hotfixes applied to `main` must be merged back into `dev` to avoid regressions.
- A clean and readable `dev` history improves maintainability and collaboration.

---

## Dev Branch Review
- Feature work is grouped and understandable.
- Squashed commits reduce noise in `dev`.
- Hotfixes applied to `main` are merged back into `dev`, keeping branches aligned.
