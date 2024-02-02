

# Divide and Conquer : Recurrence Relation
def rodcut(n, p):
    if n == 0:
        return 0
    else:
        r = -1
        for i in range(1, n + 1):
            r = max(r, p[i] + rodcut(n - i, p))
        return r


# Dynamic Programming : Memoization
def rodcut(n, p, r):
    if r[n] < 0:
        if n == 0:
            r[n] = 0
        else :
            r[n] = -1
            for i in range(1, n + 1):
                r[n] = max(r[n], p[i] + rodcut(n - i, p, r))
    return r[n]


# Dynamic Programming : Tabulization
def rodcut(n, p):
    r = [0] * (n + 1)
    for i in range(1, n + 1):
        r[i] = -1
        for j in range(1, i + 1):
            r[i] = max(r[i], r[i - j] + p[j])
    return r[n]


